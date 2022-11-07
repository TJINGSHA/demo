package com.dht.test;

import com.dht.dao.RoleMapper;
import com.dht.util.MyBatisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class MyBatisUtilsTest {

    @Test
    void openSession() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/beans.xml");
        RoleMapper roleMapper = (RoleMapper) context.getBean("roleMapper");
        System.out.println(roleMapper.selectByExample(null));
    }

    @Test
    void generatorCode() {
        /*MyBatisUtils.generatorCode();*/
    }
}