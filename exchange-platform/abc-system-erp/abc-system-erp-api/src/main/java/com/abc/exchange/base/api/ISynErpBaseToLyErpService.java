package com.abc.exchange.base.api;

public interface ISynErpBaseToLyErpService {

    /**
     * ERP基础数据-商品 => LyERP-商品
     *
     * @param goodsId 商品ID
     */
    void synErpGoodsToLyErpGoods(Long goodsId);
}
