package io.walker.planes.myehbadminton.model.item;

import io.walker.planes.myehbadminton.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 品牌
 *
 * @author Planeswalker23
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Brand extends BaseModel<Long> {

    private static final long serialVersionUID = -936500777524629319L;

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * logo
     */
    private String logo;

    /**
     * 国家
     */
    private String country;
}
