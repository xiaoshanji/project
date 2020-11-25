package com.shanji.over.user;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @version: V1.0
 * @className: UserController
 * @packageName: com.shanji.over.user
 * @data: 2020/10/30 21:10
 * @description:
 */
@Controller
@RequestMapping("/user")
public class UserController
{
    @RequestMapping("/save")
//    @RequiresRoles("user")
    @RequiresPermissions("user:add:*")
    public String register(String username,String password)
    {

        Subject subject = SecurityUtils.getSubject();

        if (subject.hasRole("user"))
        {
            System.out.println("保存成功");
        }
        else
        {
            System.out.println("无权访问");
        }
        return "redirect:/index.jsp";
    }
}
