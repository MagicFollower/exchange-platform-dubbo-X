package com.abc.platform.controller;

import com.abc.platform.base.dal.entity.Goods;
import com.abc.platform.base.dal.persistence.GoodsMapper;
import com.abc.platform.business.dal.entity.GroupSdbOrd;
import com.abc.platform.business.dal.persistence.GroupSdbOrdMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 测试多数据源环境下，使用@Transactional注解时事务失效的问题
 */
@RestController
@RequestMapping("/erp-demo")
@RequiredArgsConstructor
public class DemoController {

    private final GoodsMapper goodsMapper;
    private final GroupSdbOrdMapper groupSdbOrdMapper;

    @PostMapping("/api01")
    @Transactional(rollbackFor = Exception.class, transactionManager = "platformTransactionManagerBusiness")
    public String api01() {
        Goods goods = new Goods();
        goods.setId(ThreadLocalRandom.current().nextLong(0, Long.MAX_VALUE));
        goods.setCode(RandomStringUtils.randomAlphabetic(10));
        goods.setName(RandomStringUtils.randomAlphabetic(10));
        goodsMapper.insert(goods);

        GroupSdbOrd groupSdbOrd = new GroupSdbOrd();
        groupSdbOrd.setId(ThreadLocalRandom.current().nextLong(0, Long.MAX_VALUE));
        groupSdbOrd.setBillNo(RandomStringUtils.randomAlphabetic(10));
        groupSdbOrdMapper.insert(groupSdbOrd);

        int x = 1 / 0;

        GroupSdbOrd groupSdbOrd1 = new GroupSdbOrd();
        groupSdbOrd1.setId(ThreadLocalRandom.current().nextLong(0, Long.MAX_VALUE));
        groupSdbOrd1.setBillNo(RandomStringUtils.randomAlphabetic(10));
        groupSdbOrdMapper.insert(groupSdbOrd1);
        return "200";
    }
}
