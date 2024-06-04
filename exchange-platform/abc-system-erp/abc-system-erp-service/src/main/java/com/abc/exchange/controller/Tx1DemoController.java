package com.abc.exchange.controller;

import com.abc.exchange.base.dal.entity.Goods;
import com.abc.exchange.base.dal.persistence.GoodsMapper;
import com.abc.exchange.business.dal.entity.GroupSdbOrd;
import com.abc.exchange.business.dal.persistence.GroupSdbOrdMapper;
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
public class Tx1DemoController {
    private final GoodsMapper goodsMapper;
    private final GroupSdbOrdMapper groupSdbOrdMapper;

    /**
     * 手动控制事务提交与回滚的注意事项：
     * <pre>
     * 1.PlatformTransactionManager是一个接口，继承了TransactionManager接口；
     * 2.PlatformTransactionManager提供了三个API：开启事务(获取事务_返回TransactionStatus)、提交事务commit、混滚事务rollback；
     * 3.提交与混滚顺序要和声明事务顺序相反。
     * </pre>
     */
    private PlatformTransactionManager txManagerBase;
    private PlatformTransactionManager txManagerBusiness;

    @PostMapping("/api01")
    public String api01AndSendMessage() throws BizException {
        TransactionDefinition defaultTxDefinition = new DefaultTransactionDefinition();
        TransactionStatus txStatusBase = txManagerBase.getTransaction(defaultTxDefinition);
        TransactionStatus txStatusBusiness = txManagerBusiness.getTransaction(defaultTxDefinition);
        try {
            int x = 10 / 0;

            Goods goods = new Goods();
            goods.setId(ThreadLocalRandom.current().nextLong(0, Long.MAX_VALUE));
            goods.setCode("code-" + RandomStringUtils.randomAlphabetic(10));
            goods.setName("name-" + RandomStringUtils.randomAlphabetic(10));
            goodsMapper.insert(goods);

            GroupSdbOrd groupSdbOrd = new GroupSdbOrd();
            groupSdbOrd.setId(ThreadLocalRandom.current().nextLong(0, Long.MAX_VALUE));
            groupSdbOrd.setBillNo("billNo-" + RandomStringUtils.randomAlphabetic(10));
            groupSdbOrdMapper.insertSelective(groupSdbOrd);
        } catch (BizException e) {
            txManagerBusiness.rollback(txStatusBusiness);
            txManagerBase.rollback(txStatusBase);
            throw e;
        } catch (Exception e) {
            txManagerBusiness.rollback(txStatusBusiness);
            txManagerBase.rollback(txStatusBase);
            throw new BizException(SystemRetCodeConstants.OP_FAILED);
        }
        txManagerBusiness.commit(txStatusBusiness);
        txManagerBase.commit(txStatusBase);
        sendMessage();
        return "200";
    }

    public void sendMessage() throws BizException {
        try {
            System.out.println("开始发送消息...");
            Thread.sleep(2000);
            System.out.println("消息发送成功！");
        } catch (Exception e) {
            throw new BizException(SystemRetCodeConstants.OP_FAILED);
        }
    }

    @Autowired
    public void setTxManagerBase(@Qualifier("platformTransactionManagerBase") PlatformTransactionManager transactionManager) {
        this.txManagerBase = transactionManager;
    }

    @Autowired
    public void setTxManagerBusiness(@Qualifier("platformTransactionManagerBusiness") PlatformTransactionManager transactionManager) {
        this.txManagerBusiness = transactionManager;
    }
}
