package io.walker.planes.myehbadminton.model.item.dto;

import io.walker.planes.myehbadminton.common.constant.dict.ItemTypeDict;
import lombok.Data;

import java.io.Serializable;

/**
 * 商品型号
 *
 * @author Planeswalker23
 */
@Data
public class ItemSkuDesc implements Serializable {

    private static final long serialVersionUID = 6729017413055546954L;

    /**
     * 商品类型
     * {@link ItemTypeDict}
     */
    private String itemTypeDict;
}
