package cn.lz.spring.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPo {
    private Integer id;

    private String name;

    private String account;

    // 测试sql拦截临时添加
    public String getPermissionLevel() {
        return "1";
    }
}