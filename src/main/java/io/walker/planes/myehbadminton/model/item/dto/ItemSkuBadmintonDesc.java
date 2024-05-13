package io.walker.planes.myehbadminton.model.item.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品型号
 *
 * @author Planeswalker23
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ItemSkuBadmintonDesc extends ItemSkuDesc {

    private static final long serialVersionUID = -2289064721089943879L;

    /**
     * 毛片等级
     * {@link io.walker.planes.myehbadminton.constant.dict.BadmintonFeatherLevelDict}
     */
    private String featherLevel;

    /**
     * 毛片物种
     * {@link io.walker.planes.myehbadminton.constant.dict.BadmintonFeatherSpeciesDict}
     */
    private String featherSpeciesDict;

    /**
     * 毛片形状
     * {@link io.walker.planes.myehbadminton.constant.dict.BadmintonFeatherShapeDict}
     */
    private String featherShapeDict;
}
