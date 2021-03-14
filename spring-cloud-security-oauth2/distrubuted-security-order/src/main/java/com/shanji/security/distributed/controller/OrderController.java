package com.shanji.security.distributed.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version: V1.0
 * @className: OrderController
 * @packageName: com.shanji.security.distributed.controller
 * @data: 2021/3/6 16:09
 * @description:
 */
@RestController
@RequestMapping("/order")
public class OrderController
{
    @GetMapping("/r1")
    @PreAuthorize("hasAnyAuthority('order')")
    public String r1()
    {
        String userNmae = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userNmae + "访问资源/order/r1成功";
    }
}
