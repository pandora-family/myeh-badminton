package io.walker.planes.myehbadminton.model.item;

import io.walker.planes.myehbadminton.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 商品价格
 *
 * @author Planeswalker23
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ItemPrice extends BaseModel<Long> {

    private static final long serialVersionUID = -7474549012459515099L;

    /**
     * 商品型号
     * {@link ItemSku}
     */
    private Long itemSkuId;

    /**
     * 标价
     */
    private BigDecimal priceAmt;

    /**
     * 价格时间
     */
    private LocalDateTime priceAt;
}
