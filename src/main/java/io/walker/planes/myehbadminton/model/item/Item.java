package io.walker.planes.myehbadminton.model.item;

import io.walker.planes.myehbadminton.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品
 *
 * @author Planeswalker23
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Item extends BaseModel<Long> {

    private static final long serialVersionUID = -3645544781856688972L;

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 品牌
     * {@link Brand}
     */
    private Long brandId;

    /**
     * 商品类型
     * {@link io.walker.planes.myehbadminton.constant.dict.ItemTypeDict}
     */
    private String itemTypeDict;

    /**
     * 描述
     * {@link io.walker.planes.myehbadminton.model.item.ItemSku}
     */
    private Long itemSkuId;
}
