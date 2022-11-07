package com.dht.dao;

import com.dht.pojo.UserRoleVO;
import com.dht.pojo.UserRoleVOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRoleMapper {
    long countByExample(UserRoleVOExample example);

    int deleteByExample(UserRoleVOExample example);

    int deleteByPrimaryKey(Integer urid);

    int insert(UserRoleVO record);

    int insertSelective(UserRoleVO record);

    List<UserRoleVO> selectByExample(UserRoleVOExample example);

    UserRoleVO selectByPrimaryKey(Integer urid);

    int updateByExampleSelective(@Param("record") UserRoleVO record, @Param("example") UserRoleVOExample example);

    int updateByExample(@Param("record") UserRoleVO record, @Param("example") UserRoleVOExample example);

    int updateByPrimaryKeySelective(UserRoleVO record);

    int updateByPrimaryKey(UserRoleVO record);
}