package cn.lz.spring.dao;

import cn.lz.spring.po.DepartmentPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(@Param("id") Integer id, @Param("name") String name, @Param("code") String code);

    int insertSelective(DepartmentPo record);

    DepartmentPo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DepartmentPo record);

    int updateByPrimaryKey(DepartmentPo record);
}