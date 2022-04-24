package cn.lz.spring.controller;

import cn.lz.spring.dao.DepartmentDao;
import cn.lz.spring.po.DepartmentPo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("department")
public class DepartmentController implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @RequestMapping("select")
    public DepartmentPo select(@Param("id") Integer id) {
        DepartmentDao departmentDao = (DepartmentDao) applicationContext.getBean("departmentDao");
        return departmentDao.selectByPrimaryKey(id);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
