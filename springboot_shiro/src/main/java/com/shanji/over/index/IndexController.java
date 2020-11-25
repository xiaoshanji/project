package com.shanji.over.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version: V1.0
 * @className: IndexController
 * @packageName: com.shanji.over.index
 * @data: 2020/10/30 20:18
 * @description:
 */
@Controller
public class IndexController
{
    @GetMapping("/")
    public String index()
    {
        return "forward:login.jsp";
    }
}
