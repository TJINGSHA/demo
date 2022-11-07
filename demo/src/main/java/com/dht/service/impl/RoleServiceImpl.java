package com.dht.service.impl;

import com.dht.constant.ResultConstants;
import com.dht.constant.RoleConstants;
import com.dht.dao.PermMapper;
import com.dht.dao.RoleMapper;
import com.dht.dao.RolePermMapper;
import com.dht.pojo.*;
import com.dht.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermMapper permMapper;
    @Autowired
    private RolePermMapper rolePermMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public RoleVO getRole(int id) throws Exception {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<RoleVO> getRoleList() throws Exception {
        RoleVOExample example = new RoleVOExample();
        RoleVOExample.Criteria criteria = example.createCriteria();
        criteria.andRstatusEqualTo(RoleConstants.DEFAULTSTATUS);
        return roleMapper.selectByExample(example);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<PermVO> getPermListByRole(int id) throws Exception {
        RolePermVOExample example1 = new RolePermVOExample();
        RolePermVOExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andRpridEqualTo(id);
        List<Integer> pids = rolePermMapper.selectByExample(example1).stream().map(RolePermVO::getRppid).collect(Collectors.toList());

        PermVOExample example = new PermVOExample();
        PermVOExample.Criteria criteria = example.createCriteria();
        criteria.andPidIn(pids);
        return permMapper.selectByExample(example);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, Object> updRoleRefPerm(int rid, int[] pids) throws Exception {

        RolePermVOExample example = new RolePermVOExample();
        RolePermVOExample.Criteria criteria = example.createCriteria();
        criteria.andRpidEqualTo(rid);
        rolePermMapper.deleteByExample(example);

        if (pids != null && pids.length > 0) {
            int count = 0;
            for (int pid : pids) {
                RolePermVO record = new RolePermVO();
                record.setRppid(pid);
                record.setRprid(rid);
                count += rolePermMapper.insertSelective(record);
            }
            if (count == pids.length) {
                return ResultConstants.getResult(RoleConstants.UPDATEROLEREFPERMSUCCESS);
            } else {
                return ResultConstants.getResult(RoleConstants.UPDATEROLEREFPERMFAIL);
            }
        }

        return ResultConstants.getResult(RoleConstants.UPDATEROLEREFPERMCLEAR);
    }
}
