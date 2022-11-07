package com.dht.dao;

import com.dht.pojo.RoleVO;
import com.dht.pojo.RoleVOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleMapper {
    long countByExample(RoleVOExample example);

    int deleteByExample(RoleVOExample example);

    int deleteByPrimaryKey(Integer rid);

    int insert(RoleVO record);

    int insertSelective(RoleVO record);

    List<RoleVO> selectByExample(RoleVOExample example);

    RoleVO selectByPrimaryKey(Integer rid);

    int updateByExampleSelective(@Param("record") RoleVO record, @Param("example") RoleVOExample example);

    int updateByExample(@Param("record") RoleVO record, @Param("example") RoleVOExample example);

    int updateByPrimaryKeySelective(RoleVO record);

    int updateByPrimaryKey(RoleVO record);
}