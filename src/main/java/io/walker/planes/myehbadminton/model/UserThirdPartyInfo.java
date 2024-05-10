package io.walker.planes.myehbadminton.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Planeswalker23
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserThirdPartyInfo extends BaseModel<Long> {

    /**
     * 关联用户ID
     * {@link UserInfo}
     */
    private Long userInfoId;

    /**
     * 外部编码
     */
    private String outCode;
}
