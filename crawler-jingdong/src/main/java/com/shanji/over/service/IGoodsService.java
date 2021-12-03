package com.shanji.over.service;

import com.shanji.over.goods.Goods;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author astupidcoder
 * @since 2021-12-03
 */
public interface IGoodsService extends IService<Goods> {
    public void saveGoods(String name,Integer number) throws Exception;
}
