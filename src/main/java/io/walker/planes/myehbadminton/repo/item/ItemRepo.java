package io.walker.planes.myehbadminton.repo.item;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.walker.planes.myehbadminton.model.item.Item;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Planeswalker23
 */
@Mapper
public interface ItemRepo extends BaseMapper<Item> {

    default Item selectByName(String name) {
        return this.selectOne(new QueryWrapper<Item>().lambda().eq(Item::getName, name), false);
    }
}
