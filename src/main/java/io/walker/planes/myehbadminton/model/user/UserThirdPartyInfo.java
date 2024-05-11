package io.walker.planes.myehbadminton.model.user;

import io.walker.planes.myehbadminton.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Planeswalker23
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserThirdPartyInfo extends BaseModel<Long> {

    private static final long serialVersionUID = -1682391825788359967L;

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
