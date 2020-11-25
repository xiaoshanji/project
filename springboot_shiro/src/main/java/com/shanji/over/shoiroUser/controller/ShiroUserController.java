package com.shanji.over.shoiroUser.controller;


import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import com.shanji.over.shoiroUser.entity.ShiroUser;
import com.shanji.over.shoiroUser.service.ShiroUserService;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author vicente
 * @since 2020-11-01
 */
@Controller
@RequestMapping("/shiro-user")
public class ShiroUserController
{

    @Autowired
    private ShiroUserService shiroUserServiceImpl;

    @RequestMapping("/getImage")
    public void getImage(HttpSession session, HttpServletResponse response) throws Exception
    {
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(100, 30, 4, 4);
        session.setAttribute("code",captcha.getCode());
        response.setContentType("image/png");
        captcha.write(response.getOutputStream());
    }

    @RequestMapping("/login")
    public String login(String username,String password,String code,HttpSession session) {

        try {

            if (session.getAttribute("code").toString().equalsIgnoreCase(code)) {
                Subject subject = SecurityUtils.getSubject();

                subject.login(new UsernamePasswordToken(username, password));
                System.out.println("登录成功");
                return "redirect:/index.jsp";
            }
            else
            {
                throw new RuntimeException("验证码错误");
            }
        }
        catch(UnknownAccountException e)
        {
            System.out.println("用户名错误");
            e.printStackTrace();
        }
        catch(IncorrectCredentialsException e)
        {
            System.out.println("密码错误");
            e.printStackTrace();
        }
        catch (RuntimeException ex)
        {
            System.out.println("验证码错误");
            ex.printStackTrace();
        }
        return "redirect:/login.jsp";
    }

    @RequestMapping("/register")
    public String register(ShiroUser user)
    {

        boolean save = shiroUserServiceImpl.register(user);

        if(save)
        {
            System.out.println("注册成功");
        }
        else
        {
            System.out.println("注册失败");
        }

        return "redirect:/login.jsp";
    }

    @RequestMapping("/logout")
    public String logout()
    {
        Subject subject = SecurityUtils.getSubject();
        // 退出登录
        subject.logout();
        return "redirect:/login.jsp";
    }
}
