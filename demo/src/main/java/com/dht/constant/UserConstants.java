package com.dht.constant;

public interface UserConstants {
    int DEFAULTSTATUS = 1;
    int LOCKSTATUS = 0;
    int DEFAULTROLE = 0;
    String USERPREFIX = "USER";

    //注册业务
    //手机号码已注册
    String EXISTLOGINID = "EXISTLOGINID";
    //手机号码未注册
    String NOTEXISTLOGINID = "NOTEXISTLOGINID";
    //注册成功
    String REGUSERSUCCESS = "REGUSERSUCCESS";
    //注册失败
    String REGUSERFAIL = "REGUSERFAIL";

    //更新角色
    //清除角色信息
    String UPDATEUSERREFROLECLEAR = "UPDATEUSERREFROLECLEAR";
    //更新角色更改
    String UPDATEUSERREFROLESUCCESS = "UPDATEUSERREFROLESUCCESS";
    //更新角色失败
    String UPDATEUSERREFROLEFAIL = "UPDATEUSERREFROLEFAIL";

    //登录业务
    //登录账号不存在
    String LOGINIDNOTEXIST = "LOGINIDNOTEXIST";
    //密码错误
    String PASSWORDERROR = "PASSWORDERROR";
    //用户被锁定
    String USERLOCKED = "USERLOCKED";
    //登录成功
    String LOGINUSERSUCCESS = "LOGINUSERSUCCESS";
}
