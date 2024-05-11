package io.walker.planes.myehbadminton.model.item;

import io.walker.planes.myehbadminton.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品型号
 *
 * @author Planeswalker23
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ItemSku extends BaseModel<Long> {

    private static final long serialVersionUID = -6294085644078326315L;

    /**
     * 编码
     */
    private String code;

    /**
     * 商品
     * {@link Item}
     */
    private Long itemId;

    /**
     * 描述
     * {@link io.walker.planes.myehbadminton.model.item.dto.ItemSkuDesc}
     */
    private String description;
}
