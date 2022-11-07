package com.dht.constant;

import java.util.HashMap;
import java.util.Map;

public class ResultConstants {

    private static Map<String, Map<String,Object>> results = null;

    static {
        results = new HashMap<>();
        Map<String,Object> result;

        //配置文件
        result = new HashMap<>();
        result.put(SystemConstants.CODE,200);
        result.put(SystemConstants.MESSAGE,"新书入库成功");
        results.put(BookConstants.ADDBOOKSUCCESS,result);

        result = new HashMap<>();
        result.put(SystemConstants.CODE,301);
        result.put(SystemConstants.MESSAGE,"新书入库失败");
        results.put(BookConstants.ADDBOOKFAIL,result);

        result = new HashMap<>();
        result.put(SystemConstants.CODE,200);
        result.put(SystemConstants.MESSAGE,"所属权限清空");
        results.put(RoleConstants.UPDATEROLEREFPERMCLEAR,result);

        result = new HashMap<>();
        result.put(SystemConstants.CODE,200);
        result.put(SystemConstants.MESSAGE,"权限更新成功");
        results.put(RoleConstants.UPDATEROLEREFPERMSUCCESS,result);

        result = new HashMap<>();
        result.put(SystemConstants.CODE,401);
        result.put(SystemConstants.MESSAGE,"权限更新失败");
        results.put(RoleConstants.UPDATEROLEREFPERMFAIL,result);

        result = new HashMap<>();
        result.put(SystemConstants.CODE,200);
        result.put(SystemConstants.MESSAGE,"该手机号可以使用");
        results.put(UserConstants.NOTEXISTLOGINID,result);

        result = new HashMap<>();
        result.put(SystemConstants.CODE,402);
        result.put(SystemConstants.MESSAGE,"该手机号已被注册");
        results.put(UserConstants.EXISTLOGINID,result);

        result = new HashMap<>();
        result.put(SystemConstants.CODE,200);
        result.put(SystemConstants.MESSAGE,"新用户注册成功");
        results.put(UserConstants.REGUSERSUCCESS,result);

        result = new HashMap<>();
        result.put(SystemConstants.CODE,403);
        result.put(SystemConstants.MESSAGE,"新用户注册失败");
        results.put(UserConstants.REGUSERFAIL,result);

        result = new HashMap<>();
        result.put(SystemConstants.CODE,200);
        result.put(SystemConstants.MESSAGE,"所属角色清空");
        results.put(UserConstants.UPDATEUSERREFROLECLEAR,result);

        result = new HashMap<>();
        result.put(SystemConstants.CODE,200);
        result.put(SystemConstants.MESSAGE,"角色更新成功");
        results.put(UserConstants.UPDATEUSERREFROLESUCCESS,result);

        result = new HashMap<>();
        result.put(SystemConstants.CODE,404);
        result.put(SystemConstants.MESSAGE,"角色s更新失败");
        results.put(UserConstants.UPDATEUSERREFROLEFAIL,result);

        result = new HashMap<>();
        result.put(SystemConstants.CODE,405);
        result.put(SystemConstants.MESSAGE,"登录账号不存在");
        results.put(UserConstants.LOGINIDNOTEXIST,result);

        result = new HashMap<>();
        result.put(SystemConstants.CODE,406);
        result.put(SystemConstants.MESSAGE,"密码错误");
        results.put(UserConstants.PASSWORDERROR,result);

        result = new HashMap<>();
        result.put(SystemConstants.CODE,407);
        result.put(SystemConstants.MESSAGE,"用户被锁定");
        results.put(UserConstants.USERLOCKED,result);

        result = new HashMap<>();
        result.put(SystemConstants.CODE,200);
        result.put(SystemConstants.MESSAGE,"登录成功");
        results.put(UserConstants.LOGINUSERSUCCESS,result);
    }

    public static Map<String,Object> getResult(String key){
        return results.get(key);
    }

}
