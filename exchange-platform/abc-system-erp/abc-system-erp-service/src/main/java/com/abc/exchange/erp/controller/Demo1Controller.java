package com.abc.exchange.erp.controller;

import com.abc.exchange.erp.base.dal.entity.Goods;
import com.abc.exchange.erp.base.dal.persistence.GoodsMapper;
import com.abc.exchange.erp.business.dal.persistence.GroupSdbOrdMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

/**
 * 测试insert、insertSelective、insertUseGeneratedKeys、insertList的主键生成方案
 * 1.当没有使用@KeySql注解时 + DB指定主键自增时，四个都可以使用DB主键自增；
 * 2.当使用@KeySql注解指定ID策略时，insert、insertSelective会使用主键策略，insertUseGeneratedKeys、insertList不会使用ID生成方案（依赖DB主键策略）
 */
@RestController
@RequestMapping("/erp-demo1")
@RequiredArgsConstructor
public class Demo1Controller {

    private final GoodsMapper goodsMapper;
    private final GroupSdbOrdMapper groupSdbOrdMapper;

    /**
     * 均支持使用主键自增策略
     *
     * @return 200
     */
    @PostMapping("/api01")
    public String api01() {
        Goods goods = new Goods();
        // goods.setId(ThreadLocalRandom.current().nextLong(0, Long.MAX_VALUE));
//        goods.setCode(RandomStringUtils.randomAlphabetic(10));
//        goods.setName(RandomStringUtils.randomAlphabetic(10));
//        goodsMapper.insert(goods);
//        goods = new Goods();
//        // goods.setId(ThreadLocalRandom.current().nextLong(0, Long.MAX_VALUE));
//        goods.setCode(RandomStringUtils.randomAlphabetic(10));
//        goods.setName(RandomStringUtils.randomAlphabetic(10));
//        goodsMapper.insertSelective(goods);
//        goods = new Goods();
//        // goods.setId(ThreadLocalRandom.current().nextLong(0, Long.MAX_VALUE));
//        goods.setCode(RandomStringUtils.randomAlphabetic(10));
//        goods.setName(RandomStringUtils.randomAlphabetic(10));
//        goodsMapper.insertUseGeneratedKeys(goods);
        // goods.setId(ThreadLocalRandom.current().nextLong(0, Long.MAX_VALUE));
        goods.setCode(RandomStringUtils.randomAlphabetic(10));
        goods.setName(RandomStringUtils.randomAlphabetic(10));
        goodsMapper.batchInsertGenIdSelective(Collections.singletonList(goods));

        return "200";
    }
}
