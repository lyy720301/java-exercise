package cn.lz.spring.util;

import cn.lz.spring.po.UserPo;

public class AuthUtil {

    /**
     * 构造一个current user 对象
     *
     * @return
     */
    public static UserPo getCurrentUser() {
        return new UserPo(1, "test", "test");
    }
}
