package io.walker.planes.myehbadminton.repo.item;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.walker.planes.myehbadminton.model.item.ItemPrice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Planeswalker23
 */
@Mapper
public interface ItemPriceRepo extends BaseMapper<ItemPrice> {

    default List<ItemPrice> selectByItemSkuId(Long itemSkuId) {
        return this.selectList(new QueryWrapper<ItemPrice>().lambda()
                .eq(ItemPrice::getItemSkuId, itemSkuId)
                .orderByDesc(ItemPrice::getPriceAt));
    }
}
