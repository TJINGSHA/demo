package com.dht.dao;

import com.dht.pojo.PermVO;
import com.dht.pojo.PermVOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PermMapper {
    long countByExample(PermVOExample example);

    int deleteByExample(PermVOExample example);

    int deleteByPrimaryKey(Integer pid);

    int insert(PermVO record);

    int insertSelective(PermVO record);

    List<PermVO> selectByExample(PermVOExample example);

    PermVO selectByPrimaryKey(Integer pid);

    int updateByExampleSelective(@Param("record") PermVO record, @Param("example") PermVOExample example);

    int updateByExample(@Param("record") PermVO record, @Param("example") PermVOExample example);

    int updateByPrimaryKeySelective(PermVO record);

    int updateByPrimaryKey(PermVO record);
}