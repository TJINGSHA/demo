package com.dht.service.impl;

import com.dht.constant.ResultConstants;
import com.dht.constant.SystemConstants;
import com.dht.constant.UserConstants;
import com.dht.dao.RoleMapper;
import com.dht.dao.UserMapper;
import com.dht.dao.UserRoleMapper;
import com.dht.pojo.*;
import com.dht.service.UserService;
import com.dht.util.PagerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> existLoginId(String loginId) throws Exception {
        UserVOExample example = new UserVOExample();
        UserVOExample.Criteria criteria = example.createCriteria();
        criteria.andUloginidEqualTo(loginId);
        if (userMapper.countByExample(example) == 0) {
            return ResultConstants.getResult(UserConstants.NOTEXISTLOGINID);
        } else {
            return ResultConstants.getResult(UserConstants.EXISTLOGINID);
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> loginUser(String loginId, String password) throws Exception {
        Map<String, Object> result = existLoginId(loginId);
        if (200 == (int) result.get(SystemConstants.CODE)) {
            return ResultConstants.getResult(UserConstants.LOGINIDNOTEXIST);
        } else {
            UserVO userVO = userMapper.selectByLoginId(loginId);
            if (UserConstants.LOCKSTATUS == userVO.getUstatus()) {
                return ResultConstants.getResult(UserConstants.USERLOCKED);
            }else {
                if (userVO.getUpwd().equals(DigestUtils.md5DigestAsHex((password + userVO.getUsalt()).getBytes()))) {
                    result = ResultConstants.getResult(UserConstants.LOGINUSERSUCCESS);
                    //角色
                    userVO.setRoleVOList(getRoleListByUser(userVO.getUid()));
                    //权限对应路由列表
                    userVO.setRouteList(getRoutListByUser(userVO.getUid()));
                    //封装登录用户对象信息
                    result.put(SystemConstants.USER, userVO);
                    return result;
                } else {
                    return ResultConstants.getResult(UserConstants.PASSWORDERROR);
                }
            }
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, Object> regUser(String loginId, String password, String path) throws Exception {
        Map<String, Object> result = existLoginId(loginId);
        if (200 == (int) result.get(SystemConstants.CODE)) {
            UserVO record = new UserVO();
            record.setUlasttime(new Timestamp(System.currentTimeMillis()));
            record.setUstatus(UserConstants.DEFAULTSTATUS);
            record.setUloginid(loginId);
            record.setUname(UserConstants.USERPREFIX + loginId);
            record.setUsalt(UUID.randomUUID().toString().replace("-", "."));
            record.setUpwd(DigestUtils.md5DigestAsHex((password + record.getUsalt()).getBytes()));
            if (userMapper.insert(record) == 1) {
                UserRoleVO userRoleVO = new UserRoleVO();
                userRoleVO.setUrid(record.getUid());
                userRoleVO.setUrrid(UserConstants.DEFAULTROLE);
                userRoleMapper.insert(userRoleVO);
                return ResultConstants.getResult(UserConstants.REGUSERSUCCESS);
            } else {
                return ResultConstants.getResult(UserConstants.REGUSERFAIL);
            }
        } else {
            return result;
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public UserVO getUser(int id) throws Exception {
        UserVO userVO = userMapper.selectByPrimaryKey(id);
        userVO.setRoleVOList(getRoleListByUser(id));
        //路由空着userVO.setRouteList(
        return userVO;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public PagerUtils getUserPager(int index, int size, int step, String key) throws Exception {
        UserVOExample example = new UserVOExample();
        UserVOExample.Criteria criteria = example.createCriteria();
        criteria.andUstatusEqualTo(UserConstants.DEFAULTSTATUS);
        if (key != null && !key.isEmpty()) {
            criteria.andUloginidEqualTo(key + "%");
        }
        int count = (int) userMapper.countByExample(example);
        PagerUtils pagerUtils = null;
        if (count == 0) {
            pagerUtils = new PagerUtils();
        } else {
            pagerUtils = new PagerUtils(index-1,size,count,step);
            example.setStart(pagerUtils.getPageNumber() * pagerUtils.getPageSize());
            example.setLimit(pagerUtils.getPageSize());
            List<UserVO> items = userMapper.selectByExample(example);
            for (UserVO item : items) {
                item.setRoleVOList(getRoleListByUser(item.getUid()));
            }
            pagerUtils.setPageItems(items);
        }
        return pagerUtils;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<RoleVO> getRoleListByUser(int id) throws Exception {
        UserRoleVOExample example = new UserRoleVOExample();
        UserRoleVOExample.Criteria criteria = example.createCriteria();
        criteria.andUruidEqualTo(id);
        List<Integer> rids = userRoleMapper.selectByExample(example).stream().map(UserRoleVO::getUrrid).collect(Collectors.toList());
        if (rids.size() == 0) {
            return new ArrayList<>();
        }
        RoleVOExample example1 = new RoleVOExample();
        RoleVOExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andRidIn(rids);
        return roleMapper.selectByExample(example1);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<String> getRoutListByUser(int id) throws Exception {
        String sql = "SELECT DISTINCT proute FROM tblpermission,tblrolepermission,tblrole,tbluserrole " +
                "WHERE pid = rppid AND rprid = rid AND rid = urrid AND uruid = " + id;
        return userMapper.selectRouteListByUid(sql);
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, Object> updUserRefRole(int id, int[] rids) throws Exception {
        UserRoleVOExample example = new UserRoleVOExample();
        UserRoleVOExample.Criteria criteria = example.createCriteria();
        criteria.andUruidEqualTo(id);
        userRoleMapper.deleteByExample(example);

        if (rids != null && rids.length > 0) {
            int count = 0;
            for (int rid : rids) {
                UserRoleVO record = new UserRoleVO();
                record.setUruid(id);
                record.setUrrid(rid);
                count += userRoleMapper.insert(record);
            }
            if (count == rids.length) {
                return ResultConstants.getResult(UserConstants.UPDATEUSERREFROLESUCCESS);
            } else {
                return ResultConstants.getResult(UserConstants.UPDATEUSERREFROLEFAIL);
            }
        }
        return ResultConstants.getResult(UserConstants.UPDATEUSERREFROLECLEAR);
    }
}
