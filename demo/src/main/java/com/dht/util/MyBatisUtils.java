package com.dht.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.ShellCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MyBatisUtils {

    private static SqlSessionFactory factory = null;

    public static SqlSession openSession() {
        if (factory == null) {
            try {
                InputStream in = Resources.getResourceAsStream("mybatis/mybatis.cfg.xml");
                factory = new SqlSessionFactoryBuilder().build(in);
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return factory.openSession(false);
    }

    public static void generatorCode() {
        try {
            List<String> warnings = new ArrayList<>();
            ConfigurationParser configurationParser = new ConfigurationParser(warnings);
            InputStream in = Resources.getResourceAsStream("mybatis/mybatis.generator.code.xml");
            Configuration configuration = configurationParser.parseConfiguration(in);
            in.close();
            ShellCallback shellCallback = new DefaultShellCallback(true);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(configuration, shellCallback, warnings);
            myBatisGenerator.generate(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}