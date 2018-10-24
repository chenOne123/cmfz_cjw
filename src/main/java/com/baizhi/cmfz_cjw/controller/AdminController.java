package com.baizhi.cmfz_cjw.controller;

import com.baizhi.cmfz_cjw.entity.Admin;
import com.baizhi.cmfz_cjw.service.AdminService;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    DefaultKaptcha defaultKaptcha;

    @RequestMapping("/login")
    public String getLogin(String name, String pwd, String yz, HttpSession session){
        System.out.println("进来了"+name+" "+pwd+" "+yz);
        String yzm = (String) session.getAttribute("yz");
        Admin admin = adminService.login(name, pwd);
        if (admin != null && yz.equalsIgnoreCase(yzm)){
            session.setAttribute("user",admin);
            return "redirect:/main/main.jsp";
        }else if(admin != null && !yz.equals(yzm)){
            String error = "验证码错误";
            session.setAttribute("error",error);
            return "login";
        }
        String error = "账号或密码错误";
        session.setAttribute("error",error);
        return "login";
    }

    @RequestMapping("/yzm")
    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception{
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            httpServletRequest.getSession().setAttribute("yz", createText);
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream =
                httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }
}
