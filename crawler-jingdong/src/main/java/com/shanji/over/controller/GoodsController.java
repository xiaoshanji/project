package com.shanji.over.controller;


import com.shanji.over.crawler.JDUtil;
import com.shanji.over.goods.Goods;
import com.shanji.over.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author astupidcoder
 * @since 2021-12-03
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;

    @RequestMapping("save")
    public void save(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String name = request.getParameter("name");
        int number = Integer.parseInt(request.getParameter("number"));
        goodsService.saveGoods(name,number);
    }
}
