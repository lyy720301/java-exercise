package cn.lz.spring.dao;

import cn.lz.spring.po.DepartmentPo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class DepartmentDao extends BaseDao<DepartmentMapper> {

    public DepartmentPo selectByPrimaryKey(Integer id) {
        log.info("selectByPrimaryKey");
        return mapper.selectByPrimaryKey(id);
    }
}
