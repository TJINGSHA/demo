package com.dht.service;

import com.dht.pojo.PermVO;
import com.dht.pojo.RoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface RoleService {

    RoleVO getRole(int id) throws Exception;

    List<RoleVO> getRoleList() throws Exception;

    List<PermVO> getPermListByRole(int id) throws Exception;

    Map<String, Object> updRoleRefPerm(int rid, int[] pids) throws Exception;

}
