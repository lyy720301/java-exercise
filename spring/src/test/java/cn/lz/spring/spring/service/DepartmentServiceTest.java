package cn.lz.spring.spring.service;

import cn.lz.spring.service.DepartmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartmentServiceTest {

    @Resource
    private DepartmentService departmentService;

    @Test
    public void test() {
        departmentService.selectById(1);
    }
}
