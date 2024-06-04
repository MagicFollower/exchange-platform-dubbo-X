package com.abc.exchange.base.api;

import com.abc.exchange.base.dto.SynErpGoodsFromLyErpGoodsDTO;

import java.util.List;

public interface ISynErpBaseFromLyErpService {

    /**
     * ERP基础数据-商品 <= LyERP-商品
     *
     * @param dtoList 商品同步DTO列表
     */
    void synErpGoodsFromLyErpGoods(List<SynErpGoodsFromLyErpGoodsDTO> dtoList);

}
