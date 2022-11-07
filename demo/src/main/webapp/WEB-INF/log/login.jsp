<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.getContextPath()}"/>
<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="${root}/static/css/bootstrap.min.css" />
    <script type="text/javascript" src="${root}/static/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="${root}/static/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${root}/static/js/index_reg.js"></script>
    <script type="text/javascript" src="${root}/static/js/index_login.js"></script>
    <title>登录</title>
    <style type="text/css">
        * {
            margin: 0;
            padding: 0;
        }

        body {
            height: 100vh;
            background: url(${root}/upload/resources/bg.jpg) no-repeat;
            background-size: cover;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .box {
            width: 350px;
            height: 350px;
            border-top: 1px solid rgba(255,255,255,0.5);
            border-left: 1px solid rgba(255,255,255,0.5);
            border-bottom: 1px solid rgba(255,255,255,0.3);
            border-right: 1px solid rgba(255,255,255,0.3);
            backdrop-filter: blur(10px);
            background: rgba(50,50,50,0.2);
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            border-radius: 10px;
        }

        .box .form1 > h2 {
            color: rgba(255,255,255,0.9);
            margin-bottom: 30px;
        }

        .box .form1 .input-box {
            display: flex;
            flex-direction: column;
            box-sizing: border-box;
            margin-bottom: 10px;
        }

        .box .form1 .input-box label {
            font-size: 13px;
            color: rgba(255,255,255,0.9);
            margin-bottom: 5px;
        }

        .box .form1 .input-box input {
            letter-spacing: 1px;
            font-size: 14px;
            box-sizing: border-box;
            width: 250px;
            height: 35px;
            border-radius: 5px;
            border: 1px solid rgba(255,255,255,0.5);
            background: rgba(255,255,255,0.2);
            outline: none;
            padding: 0 12px;
            color: rgba(255,255,255,0.9);
            transition: 0.2s;
        }

        .box .form1 .input-box input:focus {
            border: 1px solid rgba(255,255,255,0.8);
        }

        .box .form1 .btn-box {
            width: 250px;
            display: flex;
            flex-direction: column;
            align-items: start;
        }

        .box .form1 .btn-box > a {
            outline: none;
            display: block;
            width: 250px;
            text-align: end;
            text-decoration: none;
            font-size: 13px;
            color: rgba(255,255,255,0.9);
        }

        .box .form1 .btn-box > a:hover {
            color: rgba(255,255,255,1);
        }

        .box .form1 .btn-box > div {
            margin-top: 10px;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .box .form1 .btn-box > div > button {
            outline: none;
            margin-top: 10px;
            display: block;
            font-size: 14px;
            border-radius: 5px;
            transition: 0.2s;
        }

        .box .form1 .btn-box > div > button:nth-of-type(1) {
            width: 250px;
            height: 35px;
            color: rgba(255,255,255,0.9);
            border: 1px solid rgba(192, 119, 91, 0.7);
            background: rgba(192, 119, 91, 0.5);
        }

        .box .form1 .btn-box > div > button:nth-of-type(2) {
            width: 120px;
            height: 35px;
            margin-left: 10px;
            color: rgba(255,255,255,0.9);
            border: 1px solid rgba(192, 119, 91, 0.7);
            background: rgba(192, 119, 91, 0.5);
        }

        .box .form1 .btn-box > div > button:hover {
            border: 1px solid rgba(251, 128, 71, 0.7);
            background: rgba(251, 128, 71, 0.5);
        }
    </style>
    <script type="text/javascript">
       /* $(function (){
            $("#login").click(function (){
                alert("a");
                $.ajax({
                    async:false,
                    type:'post',
                    url:root+'/user/login',
                    dataType:'json',
                    data:{'loginid':loginid,'paw':paw},
                    success:function(){
                        alert('s')
                    },
                    error:function(){
                        alert('账号或密码错误');
                    }
                });
            });
        })*/
    </script>
</head>
<body>
<div class="box">
    <form class="form1" action="${root}/user/login" method="post">
    <h2>Login</h2>
    <div class="input-box">
        <label>账号</label>
        <input id="loginid" name="loginid" type="text"/>
    </div>
    <div class="input-box">
        <label>密码</label>
        <input id="password" name="password" type="password"/>
    </div>
    <div class="btn-box">
        <a href="#">忘记密码?</a>
        <div>
            <button type="submit" id="login">登录</button>
            <%--<button type="button" id="res">注册</button>--%>
        </div>
    </div>
    </form>
</div>
</body>
</html>
