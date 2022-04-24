package cn.lz.spring.spring.service;

import cn.lz.spring.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    public void testEvent() {
        Class<Object> objectClass = Object.class;
        Class<List> listClass = List.class;
        assert objectClass.isAssignableFrom(listClass);
        assert !listClass.isAssignableFrom(objectClass);
    }
}
