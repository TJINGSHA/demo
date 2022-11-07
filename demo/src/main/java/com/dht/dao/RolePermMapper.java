package com.dht.dao;

import com.dht.pojo.RolePermVO;
import com.dht.pojo.RolePermVOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RolePermMapper {
    long countByExample(RolePermVOExample example);

    int deleteByExample(RolePermVOExample example);

    int deleteByPrimaryKey(Integer rpid);

    int insert(RolePermVO record);

    int insertSelective(RolePermVO record);

    List<RolePermVO> selectByExample(RolePermVOExample example);

    RolePermVO selectByPrimaryKey(Integer rpid);

    int updateByExampleSelective(@Param("record") RolePermVO record, @Param("example") RolePermVOExample example);

    int updateByExample(@Param("record") RolePermVO record, @Param("example") RolePermVOExample example);

    int updateByPrimaryKeySelective(RolePermVO record);

    int updateByPrimaryKey(RolePermVO record);
}