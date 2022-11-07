package com.dht.service;

import com.dht.pojo.PermVO;
import com.dht.pojo.RoleVO;
import com.dht.pojo.UserVO;
import com.dht.util.PagerUtils;

import java.util.List;
import java.util.Map;

public interface UserService {
    //判断用户是否存在
    Map<String, Object> existLoginId(String loginId) throws Exception;
    //登录
    Map<String, Object> loginUser(String loginId, String password) throws Exception;
    //注册
    Map<String, Object> regUser(String loginId, String password, String path) throws Exception;
    //获取用户消息
    UserVO getUser(int id) throws Exception;
    //用户列表（非管理员）
    PagerUtils getUserPager(int index, int size, int step, String key) throws Exception;
    //所属角色列表
    List<RoleVO> getRoleListByUser(int id) throws Exception;
    //所属权限对应路由列表
    List<String> getRoutListByUser(int id) throws Exception;
    //更新用户角色
    Map<String, Object> updUserRefRole(int id, int[] rids) throws Exception;

}
