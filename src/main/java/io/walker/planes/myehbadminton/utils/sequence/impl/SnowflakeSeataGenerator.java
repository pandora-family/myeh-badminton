package io.walker.planes.myehbadminton.utils.sequence.impl;


import io.walker.planes.myehbadminton.utils.sequence.IdGenerator;

import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 基于雪花算法的生成器(完整不依赖时间戳, 解决时钟回拨问题, Seata 优化版本)<br>
 * <pre>
 * 0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 - 000000000000
 * </pre>
 * 从后往前数, 雪花算法生成的ID结构组成说明:<br><br>
 * 1. 第一部分: 从后往前数的连续 12位 是序列号位, 序列号位是一串递增的数字, 12位代表一毫秒内最多可以生成 2^12=4096 个ID<br><br>
 * 2. 第二部分: 从后往前数的连续 41位 是时间戳位, 41位的时间戳最多可以使用 2^41≈69.7 年 (我们经常会用当前时间减去一个魔法值时间戳, 一般是服务上线时间, 来使得这一部分的位数得到充分使用)<br><br>
 * 3. 第三部分: 从后往前数的连续 10位 是机器号位, 10位最多支持部署 2^10=1024 个节点 (有些实现方式会使用5位机房号和5为机器号来组成一个机器号位)<br><br>
 * 4. 第四部分: 从后往前数的连续  1位 是符号位, 由于ID是正数所以固定为0, 实际上符号位一般不会表示出来, 因为一般来说生成的ID并不会达到 64 位那么长<br><br>
 *
 * @author Planeswalker23
 * @see <a href="http://seata.io/zh-cn/blog/seata-analysis-UUID-generator.html">seata 优化版</a><br>
 * @see <a href="https://github.com/seata/seata/blob/2.x/common/src/main/java/io/seata/common/util/IdWorker.java">seata 优化版源码</a><br>
 */
public class SnowflakeSeataGenerator implements IdGenerator {

    /**
     * 魔数: 初始时间, 需要比当前时间小
     * 1691746515000 = 2023-08-11 17:35:15
     */
    private final long initTimestamp = 1691746515000L;

    /**
     * 机器ID(workId)占用的位数
     */
    private final int workerIdBits = 10;

    /**
     * 时间戳占用的位数
     */
    private final int timestampBits = 41;

    /**
     * 序列号占用的位数
     */
    private final int sequenceBits = 12;

    /**
     * 机器ID(workId)的最大值: 2^10-1 = 1023(二进制11111)<br>
     * ~(-1L << WORKER_ID_BITS): <br>
     * 1. 先取 -1 的原码   => 1 0000 0000 0000 0001<br>
     * 2. 再取 -1 的补码   => 1 1111 1111 1111 1110(反码) => 1 1111 1111 1111 1111(补码)<br>
     * 3. 左移 10 位      => 1 1111 1100 0000 0000<br>
     * 4. ~按位取反       => 0 0000 0011 1111 1111(补码)<br>
     * 5. 转化为原码      => 0 0000 0011 1111 1111(原码) = 1023<br>
     */
    private final int maxWorkerId = ~(-1 << workerIdBits);

    /**
     * business meaning: machine ID (0 ~ 1023)
     * actual layout in memory:
     * highest 1 bit: 0
     * middle 10 bit: workerId
     * lowest 53 bit: all 0
     */
    private long workerId;

    /**
     * timestamp and sequence mix in one Long
     * highest 11 bit: not used
     * middle  41 bit: timestamp
     * lowest  12 bit: sequence
     * 时间戳+序列号, 即整个雪花算法生成 ID 的后53位, 放在一起是为了方便递增
     *
     * @see AtomicLong#getAndIncrement()
     */
    private AtomicLong timestampAndSequence;

    /**
     * mask that help to extract timestamp and sequence from a long
     * <p>
     * 时间戳序列号的掩码, 防止其溢出
     */
    private final long timestampAndSequenceMask = ~(-1L << (timestampBits + sequenceBits));

    /**
     * instantiate an IdWorker using given workerId
     *
     * @param workerId if null, then will auto assign one
     */
    public SnowflakeSeataGenerator(Long workerId) {
        initTimestampAndSequence();
        initWorkerId(workerId);
    }

    public SnowflakeSeataGenerator() {
        initTimestampAndSequence();
        initWorkerId(null);
    }

    /**
     * init first timestamp and sequence immediately
     */
    private void initTimestampAndSequence() {
        long timestamp = getNewestTimestamp();
        long timestampWithSequence = timestamp << sequenceBits;
        this.timestampAndSequence = new AtomicLong(timestampWithSequence);
    }

    /**
     * init workerId
     *
     * @param workerId if null, then auto generate one
     */
    private void initWorkerId(Long workerId) {
        if (workerId == null) {
            workerId = generateWorkerId();
        }
        if (workerId > maxWorkerId || workerId < 0) {
            String message = String.format("worker Id can't be greater than %d or less than 0", maxWorkerId);
            throw new IllegalArgumentException(message);
        }
        this.workerId = workerId << (timestampBits + sequenceBits);
    }

    /**
     * get next UUID(base on snowflake algorithm), which look like:
     * highest 1 bit: always 0
     * next   10 bit: workerId
     * next   41 bit: timestamp
     * lowest 12 bit: sequence
     *
     * @return UUID
     */
    @Override
    public long nextId() {
        waitIfNecessary();
        long next = timestampAndSequence.incrementAndGet();
        long timestampWithSequence = next & timestampAndSequenceMask;
        return workerId | timestampWithSequence;
    }

    /**
     * block current thread if the QPS of acquiring UUID is too high
     * that current sequence space is exhausted
     */
    private void waitIfNecessary() {
        long currentWithSequence = timestampAndSequence.get();
        long current = currentWithSequence >>> sequenceBits;
        long newest = getNewestTimestamp();
        if (current >= newest) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException ignore) {
                // don't care
            }
        }
    }

    /**
     * get newest timestamp relative to twepoch
     */
    private long getNewestTimestamp() {
        return System.currentTimeMillis() - initTimestamp;
    }

    /**
     * auto generate workerId, try using mac first, if failed, then randomly generate one
     *
     * @return workerId
     */
    private long generateWorkerId() {
        try {
            return generateWorkerIdBaseOnMac();
        } catch (Exception e) {
            return generateRandomWorkerId();
        }
    }

    /**
     * use lowest 10 bit of available MAC as workerId
     *
     * @return workerId
     * @throws Exception when there is no available mac found
     */
    private long generateWorkerIdBaseOnMac() throws Exception {
        Enumeration<NetworkInterface> all = NetworkInterface.getNetworkInterfaces();
        while (all.hasMoreElements()) {
            NetworkInterface networkInterface = all.nextElement();
            boolean isLoopback = networkInterface.isLoopback();
            boolean isVirtual = networkInterface.isVirtual();
            if (isLoopback || isVirtual) {
                continue;
            }
            byte[] mac = networkInterface.getHardwareAddress();
            return ((mac[4] & 0B11) << 8) | (mac[5] & 0xFF);
        }
        throw new RuntimeException("no available mac found");
    }

    /**
     * randomly generate one as workerId
     *
     * @return workerId
     */
    private long generateRandomWorkerId() {
        return new Random().nextInt(maxWorkerId + 1);
    }
}
