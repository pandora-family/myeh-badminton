package io.walker.planes.myehbadminton.repo.item;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.walker.planes.myehbadminton.model.item.ItemRecommendRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Planeswalker23
 */
@Mapper
public interface ItemRecommendRecordRepo extends BaseMapper<ItemRecommendRecord> {

    default List<ItemRecommendRecord> selectByItemId(Long itemId) {
        return this.selectList(new QueryWrapper<ItemRecommendRecord>().lambda()
                .eq(ItemRecommendRecord::getItemId, itemId)
                .orderByDesc(ItemRecommendRecord::getRecommendAt));
    }
}
