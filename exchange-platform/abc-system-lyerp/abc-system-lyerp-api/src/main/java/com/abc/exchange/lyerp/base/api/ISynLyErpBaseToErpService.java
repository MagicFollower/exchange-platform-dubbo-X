package com.abc.exchange.lyerp.base.api;

public interface ISynLyErpBaseToErpService {
    /**
     * LyERP-商品 => ERP基础数据-商品
     *
     * @param goodsId 商品ID
     */
    void synLyErpGoodsToErpGoods(Long goodsId);
}
