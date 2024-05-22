package io.walker.planes.myehbadminton.model.item.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Planeswalker23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemRecommendDTO implements Serializable {


    private static final long serialVersionUID = -5142256906785856357L;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 商品名称
     */
    private String itemName;

    /**
     * 推荐人外部编码
     */
    private String userOutCode;
    
}
