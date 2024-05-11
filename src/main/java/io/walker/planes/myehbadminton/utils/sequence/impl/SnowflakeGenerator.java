package io.walker.planes.myehbadminton.utils.sequence.impl;

import io.walker.planes.myehbadminton.utils.sequence.IdGenerator;

import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.Random;

/**
 * 基于雪花算法的ID生成器
 *
 * @author Planeswalker23
 */
public class SnowflakeGenerator implements IdGenerator {

    private final long initTimestamp = 1691746515000L;

    private long lastTimestampMillis = -1L;

    private static final long WORKER_ID_BITS = 10L;

    private static final long MAX_WORKER_ID = ~(-1L << WORKER_ID_BITS);

    private final long workerId;

    private static final long SEQUENCE_BITS = 12L;

    private static final long MAX_SEQUENCE_ID = ~(-1L << SEQUENCE_BITS);

    private long sequenceId;

    private static final long LEFT_SHIFT_WORK_ID = SEQUENCE_BITS;

    private static final long LEFT_SHIFT_TIMESTAMP = SEQUENCE_BITS + WORKER_ID_BITS;

    public SnowflakeGenerator() {
        this.workerId = generateWorkerId();
        if (workerId < 0 || workerId > MAX_WORKER_ID) {
            throw new RuntimeException("id.generator.work.id.is.illegal");
        }
    }

    /**
     * 获取下一个ID序列号
     *
     * @return ID
     */
    @Override
    public synchronized long nextId() {
        // 获取当前时间戳
        // 防止时钟回拨导致生成重复ID
        long currentTimeMillis = getNewestTimestamp();

        // 生成序列号位
        if (currentTimeMillis == lastTimestampMillis) {
            sequenceId = (sequenceId + 1) & MAX_SEQUENCE_ID;

            // 若下一个ID超出每毫秒可产生最大序列号,就使用新的时间戳
            if (sequenceId == 0) {
                currentTimeMillis = nextTimestampMillis(currentTimeMillis);
            }
        } else {
            // 不在同一毫秒内连续生成ID, 从0开始生成
            sequenceId = 0;
        }

        // 记录此次生成ID的时间戳
        lastTimestampMillis = currentTimeMillis;

        // 雪花算法的核心算法: 将各个符号位左移到指定位置, 然后拼起来
        return ((currentTimeMillis - initTimestamp) << LEFT_SHIFT_TIMESTAMP) // 时间戳
                | (workerId << LEFT_SHIFT_WORK_ID) // 机器ID
                | sequenceId; // 序列号
    }

    /**
     * 自旋获取指定时间戳的下一个毫秒级时间戳
     *
     * @param lastTimestamp 指定时间戳
     * @return 大于指定时间戳
     */
    private long nextTimestampMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }

    /**
     * 获取最新时间戳, 解决时钟回拨问题
     *
     * @return 最新时间戳
     */
    private long getNewestTimestamp() {
        long currentTimeMillis = System.currentTimeMillis();

        // 不存在时钟回拨问题
        if (currentTimeMillis >= lastTimestampMillis) {
            return currentTimeMillis;
        }

        long diff = lastTimestampMillis - currentTimeMillis;
        if (diff <= 5) {
            // 时间偏差小于等于 5ms 等待两倍时间
            try {
                Thread.sleep(diff << 1);
                currentTimeMillis = System.currentTimeMillis();
            } catch (InterruptedException e) {
                // ignore
            }
        } else {
            // 时间偏差大于 5ms: 使用内部冗余的上一次使用ID生成器的毫秒级时间戳, 直到最新时间戳赶上该时间后, 替换为获取的系统时间戳
            currentTimeMillis = lastTimestampMillis;
        }
        return currentTimeMillis;
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
            boolean isLoopBack = networkInterface.isLoopback();
            boolean isVirtual = networkInterface.isVirtual();
            if (isLoopBack || isVirtual) {
                continue;
            }
            byte[] mac = networkInterface.getHardwareAddress();
            return ((mac[4] & 0B11) << 8) | (mac[5] & 0xFF);
        }
        throw new RuntimeException("id.generator.no.available.mac.found");
    }

    /**
     * randomly generate one as workerId
     *
     * @return workerId
     */
    private long generateRandomWorkerId() {
        return new Random().nextInt((int) MAX_WORKER_ID + 1);
    }
}
