package cn.lz.spring.service;

import cn.lz.spring.dao.DepartmentDao;
import cn.lz.spring.dao.DepartmentMapper;
import cn.lz.spring.po.DepartmentPo;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DepartmentService {

    @Resource
    private DepartmentDao departmentDao;

    @Resource
    private SqlSessionFactory sqlSessionFactory;

    public DepartmentPo selectById(Integer id) {
        return departmentDao.selectByPrimaryKey(id);
    }

    public Integer batchInsert(List<DepartmentPo> departmentPoList) {
        int[] count = new int[]{};
        count[0] = 0;
        try (SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);) {
            DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
            departmentPoList.forEach(po -> {
                departmentMapper.insert(po.getId(), po.getName(), po.getCode());
                count[0]++;
            });
            sqlSession.commit();
            return count[0];
        }
    }

}
