package cn.lz.spring.spring.service.event;

import cn.lz.spring.po.UserPo;
import cn.lz.spring.service.event.UserAddPublisherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserAddPublisherServiceTest {

    @Resource
    private UserAddPublisherService userAddPublisherService;

    @Test
    public void testPublish() {
        UserPo userPo = new UserPo();
        userPo.setName("addedUser");
        userAddPublisherService.insert(userPo);
    }
}
