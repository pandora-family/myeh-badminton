package io.walker.planes.myehbadminton.context;

import io.walker.planes.myehbadminton.model.user.UserInfo;

import java.util.Optional;

/**
 * @author 范逸东(东稚)
 */
public class UserContext {

    private static final ThreadLocal<UserInfo> USER_INFO = new ThreadLocal<>();

    public static UserInfo get() {
        return Optional.ofNullable(USER_INFO.get()).orElseThrow(() -> new RuntimeException("user.not.login"));
    }

    public static void set(UserInfo value) {
        USER_INFO.set(value);
    }

    public static void remove() {
        USER_INFO.remove();
    }
}
