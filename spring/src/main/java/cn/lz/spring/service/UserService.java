package cn.lz.spring.service;

import cn.lz.spring.dao.UserMapper;
import cn.lz.spring.dao.annos.RequireDataPermission;
import cn.lz.spring.po.UserPo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    @RequireDataPermission("id")
    public UserPo selectById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * mock
     */
    public int insertUser(UserPo userPo) {
        log.info("insert a user {}", userPo);
        return 1;
    }
}
