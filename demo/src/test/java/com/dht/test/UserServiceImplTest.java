package com.dht.test;

import com.dht.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/beans.xml");
    UserService userService = (UserService) ctx.getBean("userService");

    @Test
    void existLoginId() {
        String loginId = "13123123124";
        try {
            System.out.println(userService.existLoginId(loginId));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void loginUser() {
        String loginId = "13000000000";
        String password = "a123123";
        try {
            System.out.println(userService.loginUser(loginId,password));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void regUser() {
        String loginId = "13000000000";
        String password = "a123123";
        String path = "";
        try {
            System.out.println(userService.regUser(loginId,password,path));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getUser() {
    }

    @Test
    void getUserPager() {
        int index = 1;
        int size  = 5;
        int step = 5;
        String key = "";
        try {
            System.out.println(userService.getUserPager(index,size,step,key));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getRoleListByUser() {
        int uid = 4;
        try {
            System.out.println(userService.getRoleListByUser(uid));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getRoutListByUser() {
        int uid = 3;
        try {
            System.out.println(userService.getRoutListByUser(uid));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void updUserRefRole() {
        int uid = 3;
        int[] rids = {1,2};
        try {
            System.out.println(userService.updUserRefRole(uid,rids));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}