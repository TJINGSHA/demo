package com.dht.service.impl;

import com.dht.constant.PermConstants;
import com.dht.dao.PermMapper;
import com.dht.pojo.PermVO;
import com.dht.pojo.PermVOExample;
import com.dht.service.PermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("permService")
public class PermServiceImpl implements PermService {

    @Autowired
    private PermMapper permMapper;

    @Override
    public PermVO getPerm(int id) throws Exception {
        return permMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<PermVO> getPermList() throws Exception {
        PermVOExample example = new PermVOExample();
        PermVOExample.Criteria criteria = example.createCriteria();
        criteria.andPstatusEqualTo(PermConstants.DEFAULTSTATUS);
        return permMapper.selectByExample(example);
    }
}
