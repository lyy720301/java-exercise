package cn.lz.spring.dao;

import cn.lz.spring.constants.PowerConstant;
import cn.lz.spring.dao.annos.RequireDataPermission;
import cn.lz.spring.po.UserPo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(UserPo record);

    int insertSelective(UserPo record);

    @RequireDataPermission(value = "id", prop = PowerConstant.ID)
    UserPo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserPo record);

    int updateByPrimaryKey(UserPo record);
}