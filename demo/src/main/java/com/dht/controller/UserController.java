package com.dht.controller;

import com.alibaba.fastjson.JSONObject;
import com.dht.pojo.UserVO;
import com.dht.service.UserService;
import com.dht.util.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/user/code",method = RequestMethod.GET)
    public void method0(HttpServletRequest request, HttpServletResponse response) throws Exception{
        int length = 4;
        int width = 120;
        int height = 32;
        int lines = 5;

        String code = RandomUtils.authCode(length);
        System.out.println(code);
        Cookie cookie = new Cookie("code",code);
        cookie.setPath(request.getContextPath());
        cookie.setHttpOnly(true);
        cookie.setMaxAge(600);
        response.addCookie(cookie);

        //构图
        BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        //画布 默认背景色为黑色
        Graphics graphics = bufferedImage.createGraphics();
        //背景
        graphics.setColor(new Color(154,217,234));
        graphics.fillRect(0,0,width,height);
        //干扰线
        graphics.setColor(new Color(237,30,38));
        for (int i = 0; i < lines; i++) {
            int x1 = RandomUtils.getNum(width);
            int y1 = RandomUtils.getNum(height);
            int x2 = RandomUtils.getNum(width);
            int y2 = RandomUtils.getNum(height);
            graphics.drawLine(x1,y1,x2,y2);
        }
        //验证码
        Font font = new Font("黑体",1,20);
        graphics.setFont(font);
        graphics.setColor(new Color(64,73,204));
        StringBuilder strb = new StringBuilder();
        for(char chr:code.toCharArray()){
            strb.append(chr+" ");
        }
        graphics.drawString(strb.toString(),width/6,height*4/5);
        graphics.dispose();

        //写入响应流
        OutputStream outputStream = response.getOutputStream();
        ImageIO.write(bufferedImage,"jpeg",outputStream);
        outputStream.flush();
        outputStream.close();
    }

    @RequestMapping(value="/user/reg",method = RequestMethod.POST)
    public String method1(String loginId,String password) throws Exception{
        System.out.println(loginId);
        System.out.println(password);
        return null;
    }

    @RequestMapping(value="/user/login",method = RequestMethod.POST)
    public void method2(HttpServletRequest request,HttpServletResponse response) throws Exception{
        String id = request.getParameter("loginid");
        String paw = request.getParameter("password");
        /*System.out.println(id);
        System.out.println(paw);*/
        Map<String,Object> result = userService.loginUser(id,paw);

        if (200 == (int) result.get("code")) {
            UserVO user = (UserVO) result.get("user");
            request.getSession().setAttribute("user", user);
            response.sendRedirect(request.getContextPath());
        }else {
            request.getSession().setAttribute("resule", result);
            request.getRequestDispatcher("/WEB-INF/log/login.jsp").forward(request, response);
        }
    }

    @RequestMapping(value = "/user/logout",method = RequestMethod.GET)
    public void method3(HttpServletRequest request,HttpServletResponse response) throws Exception {
        request.getRequestDispatcher("/WEB-INF/log/login.jsp").forward(request,response);

    }

    @RequestMapping(value = "/user/regout",method = RequestMethod.GET)
    public void method4(HttpServletRequest request,HttpServletResponse response) throws Exception {
        request.getRequestDispatcher("/WEB-INF/log/login1.jsp").forward(request,response);
    }

    @RequestMapping(value = "/user/logoutone",method = RequestMethod.GET)
    public void method5(HttpServletRequest request,HttpServletResponse response) throws Exception {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath());
    }
}
