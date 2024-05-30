package com.abc.exchange.erp.controller;

import com.abc.exchange.erp.base.dal.entity.Goods;
import com.abc.exchange.erp.base.dal.persistence.GoodsMapper;
import com.abc.exchange.erp.business.dal.persistence.GroupSdbOrdMapper;
import com.abc.system.common.constant.SystemRetCodeConstants;
import com.abc.system.common.exception.business.BizException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
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
    private PlatformTransactionManager transactionManager;

    @PostMapping("/api01")
    public String api01AndSendMessage() throws BizException {
        TransactionDefinition definitionOne = new DefaultTransactionDefinition();
        TransactionStatus txStatus = transactionManager.getTransaction(definitionOne);
        try {
            Goods goods = new Goods();
            goods.setId(ThreadLocalRandom.current().nextLong(0, Long.MAX_VALUE));
            goods.setCode("code-" + RandomStringUtils.randomAlphabetic(10));
            goods.setName("name-" + RandomStringUtils.randomAlphabetic(10));
            goodsMapper.insert(goods);

//            int x = 1 / 0;
            transactionManager.commit(txStatus);
        } catch (BizException e) {
            transactionManager.rollback(txStatus);
            throw e;
        } catch (Exception e) {
            transactionManager.rollback(txStatus);
            throw new BizException(SystemRetCodeConstants.OP_FAILED);
        }
        sendMessage();
        return "200";
    }

    public void sendMessage() throws BizException {
        try {
            System.out.println("开始发送消息...");
            Thread.sleep(10000);
            System.out.println("消息发送成功！");
        } catch (Exception e) {
            throw new BizException(SystemRetCodeConstants.OP_FAILED);
        }
    }

    @Autowired
    public void setTransactionManager(@Qualifier("platformTransactionManagerBase") PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }
}
