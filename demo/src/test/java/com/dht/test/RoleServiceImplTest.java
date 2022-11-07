package com.dht.test;

import com.dht.dao.RoleMapper;
import com.dht.pojo.RoleVO;
import com.dht.service.RoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class RoleServiceImplTest {

    ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/beans.xml");
    RoleService roleService = (RoleService) ctx.getBean("roleService");

    @Test
    void getRole() {
        int a = 0;
        try {
            System.out.println(roleService.getRole(a));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getRoleList() {
        try {
            System.out.println(roleService.getRoleList());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getPermListByRole() {
        int rid = 2;
        try {
            System.out.println(roleService.getPermListByRole(rid));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void updRoleRefPerm() {
    }
}