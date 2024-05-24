package com.abc.exchange.lyerp.base.api;

import com.abc.exchange.lyerp.base.dto.SynLyErpGoodsFromErpGoodsDTO;

import java.util.List;

public interface ISynLyErpBaseFromErpService {
    /**
     * LyERP-商品 <= ERP基础数据-商品
     *
     * @param dtoList 商品同步DTO
     */
    void synLyErpGoodsFromErpGoods(List<SynLyErpGoodsFromErpGoodsDTO> dtoList);
}
