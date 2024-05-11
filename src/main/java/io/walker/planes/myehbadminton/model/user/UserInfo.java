package io.walker.planes.myehbadminton.model.user;

import io.walker.planes.myehbadminton.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Planeswalker23
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserInfo extends BaseModel<Long> {

    private static final long serialVersionUID = 7887573694130303195L;

    /**
     * 编码
     */
    private String code;

    /**
     * 昵称
     */
    private String username;
}
