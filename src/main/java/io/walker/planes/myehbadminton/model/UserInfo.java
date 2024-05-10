package io.walker.planes.myehbadminton.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Planeswalker23
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserInfo extends BaseModel<Long> {

    /**
     * 昵称
     */
    private String username;
}
