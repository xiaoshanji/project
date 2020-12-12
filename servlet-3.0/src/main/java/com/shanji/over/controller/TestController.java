package com.shanji.over.controller;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version: V1.0
 * @className: TestController
 * @packageName: com.shanji.over.controller
 * @data: 2020/12/12 15:44
 * @description:
 */
@Controller
@RequestMapping("/test")
public class TestController
{

    @GetMapping(value = "/01",produces =  "text/html;charset=utf-8")
    @ResponseBody
    public String test()
    {
        return "访问成功";
    }
}
