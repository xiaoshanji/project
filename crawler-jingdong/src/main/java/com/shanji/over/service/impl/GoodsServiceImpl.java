package com.shanji.over.service.impl;

import com.shanji.over.crawler.JDUtil;
import com.shanji.over.goods.Goods;
import com.shanji.over.mapper.GoodsMapper;
import com.shanji.over.service.IGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author astupidcoder
 * @since 2021-12-03
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    @Autowired
    private JDUtil jdUtil;

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public void saveGoods(String name, Integer number) throws Exception {
        List<Goods> run = jdUtil.run(name, number);
        for(Goods temp : run){
            goodsMapper.insert(temp);
        }
    }
}
