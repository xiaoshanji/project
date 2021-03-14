package com.shanji.security.distributed.user.controller;



import com.shanji.security.distributed.user.entity.User;
import com.shanji.security.distributed.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author vicente
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/user")
public class UserController
{
    @Autowired
    private UserService userServiceImpl;

    @PostMapping(value = "/success",produces = {"text/plain;charset=utf-8"})
    @ResponseBody
    public String success()
    {
        return getUserName() + "：success";
    }

    @PostMapping("/register")
    @ResponseBody
    public String register(User user)
    {
        int count = userServiceImpl.register(user);
        if(count != 1)
        {
            return "注册失败";
        }
        return "注册成功";
    }

    @GetMapping(value = "/test",produces = {"text/plain;charset=utf-8"})
    @ResponseBody
    public String test(User user)
    {
        return "测试成功";
    }


    // 登录成功后，获取用户名
    public static String getUserName()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if(principal != null && principal instanceof UserDetails)
        {
            UserDetails user = (UserDetails)principal;
            return user.getUsername();
        }
        return "";
    }
}
