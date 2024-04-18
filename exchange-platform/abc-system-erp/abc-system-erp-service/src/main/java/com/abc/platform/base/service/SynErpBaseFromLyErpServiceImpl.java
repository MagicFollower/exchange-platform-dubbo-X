package com.abc.platform.base.service;

import com.abc.platform.base.api.ISynErpBaseFromLyErpService;
import com.abc.platform.base.dto.SynErpGoodsFromLyErpGoodsDTO;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;

import java.util.List;

//@DubboService
@RequiredArgsConstructor
public class SynErpBaseFromLyErpServiceImpl implements ISynErpBaseFromLyErpService {
    @Override
    public void synErpGoodsFromLyErpGoods(List<SynErpGoodsFromLyErpGoodsDTO> dtoList) {
        // 解析dtoList存储到本地数据源
        System.out.println("Erp接收到LyERP同步的商品数据：");
        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(dtoList));
    }
}
