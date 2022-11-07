package com.dht.pojo;

import java.sql.Timestamp;
import java.util.List;

public class UserVO {
    private Integer uid;

    private Integer ustatus;

    private String uloginid;

    private String uname;

    private String upwd;

    private String usalt;

    private Timestamp ulasttime;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getUstatus() {
        return ustatus;
    }

    public void setUstatus(Integer ustatus) {
        this.ustatus = ustatus;
    }

    public String getUloginid() {
        return uloginid;
    }

    public void setUloginid(String uloginid) {
        this.uloginid = uloginid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }

    public String getUsalt() {
        return usalt;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "uid=" + uid +
                ", ustatus=" + ustatus +
                ", uloginid='" + uloginid + '\'' +
                ", uname='" + uname + '\'' +
                ", upwd='" + upwd + '\'' +
                ", usalt='" + usalt + '\'' +
                ", ulasttime=" + ulasttime +
                ", roleVOList=" + roleVOList +
                ", routeList=" + routeList +
                '}';
    }

    public List<RoleVO> getRoleVOList() {
        return roleVOList;
    }

    public void setRoleVOList(List<RoleVO> roleVOList) {
        this.roleVOList = roleVOList;
    }

    public List<String> getRouteList() {
        return routeList;
    }

    public void setRouteList(List<String> routeList) {
        this.routeList = routeList;
    }

    public void setUsalt(String usalt) {
        this.usalt = usalt;
    }

    public Timestamp getUlasttime() {
        return ulasttime;
    }

    public void setUlasttime(Timestamp ulasttime) {
        this.ulasttime = ulasttime;
    }

    private List<RoleVO> roleVOList;
    private List<String> routeList;
}