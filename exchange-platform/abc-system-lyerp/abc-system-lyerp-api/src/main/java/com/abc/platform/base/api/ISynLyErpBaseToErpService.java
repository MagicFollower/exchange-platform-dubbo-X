package com.abc.platform.base.api;

public interface ISynLyErpBaseToErpService {
    /**
     * LyERP-商品 => ERP基础数据-商品
     *
     * @param goodsId 商品ID
     */
    void synLyErpGoodsToErpGoods(Long goodsId);
}
