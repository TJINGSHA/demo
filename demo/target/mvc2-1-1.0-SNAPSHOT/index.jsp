<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.getContextPath()}"/>
<!DOCTYPE html>
<html>
<head>
    <title>首页</title>
    <link rel="stylesheet" type="text/css" href="${root}/static/css/bootstrap.min.css" />
    <script type="text/javascript" src="${root}/static/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="${root}/static/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${root}/static/js/index_reg.js"></script>
    <script type="text/javascript" src="${root}/static/js/index_login.js"></script>
</head>
<body class="container" style="margin-top:20px; ">
<input type="hidden" id="root" value="${root}">
<div class="navbar-header">
    <button type="button" class="btn btn-default navbar-toggle" data-toggle="collapse" data-target="#navbarcollapse">
        <span class="glyphicon glyphicon-align-justify"></span>
    </button>
    <a href="" class="navbar-brand">图书网</a>
</div>
<div class="collapse navbar-collapse" id="navbercollapse">
    <ul class="nav navbar-nav">
        <li><a href="">首页</a></li>
        <li><a href="" id="btn_upload">上传</a></li>
        <li><a href="" id="btn_refresh">刷新</a></li>
    </ul>
    <ul class="nav navbar-nav pull-right">
        <c:if test="${sessionScope.user==null}">
            <li><a id="a_login" href="${root}/user/logout"><span class="glyphicon glyphicon-log-in"></span>登录</a></li>
        </c:if>
        <c:if test="${sessionScope.user!=null}">
            <li class="dropdown">
                <a href="javascript:void(0);" data-toggle="dropdown">
                    <img id="img_code" style="vertical-align: middle;height: 30px;" src="${root}/upload/photos/${sessionScope.user.uid}" title="${sessionScope.user.uname}" />
                    <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="#">完善资料</a></li>
                    <li><a href="#">修改密码</a></li>
                    <li class="divider"></li>
                    <li><a href="#">安全退出</a></li>
                </ul>
            </li>
            <li><a href="${root}/user/logoutone"><span class="glyphicon glyphicon-log-out"></span>注销</a></li>
        </c:if>
        <li><a id="a_reg" href="${root}/user/regout"><span class="glyphicon glyphicon-user"></span>注册</a></li>
    </ul>
    <form action="" method="post" class="navbar-form pull-right visible-lg-inline">
        <input type="text" id="key" placeholder="请输入关键字" style="" class="form-control">
        <button id="btn_serrch" type="button" class="btn btn-default">
            <span class="glyphicon glyphicon-search"></span>搜索
        </button>
    </form>
</div>
</body>
</html>