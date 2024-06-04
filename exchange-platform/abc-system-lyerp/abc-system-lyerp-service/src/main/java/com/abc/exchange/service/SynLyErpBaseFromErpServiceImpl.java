package com.abc.exchange.service;

import com.abc.exchange.lyerp.base.api.ISynLyErpBaseFromErpService;
import com.abc.exchange.lyerp.base.dto.SynLyErpGoodsFromErpGoodsDTO;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.List;

@DubboService
@RequiredArgsConstructor
public class SynLyErpBaseFromErpServiceImpl implements ISynLyErpBaseFromErpService {
    @Override
    public void synLyErpGoodsFromErpGoods(List<SynLyErpGoodsFromErpGoodsDTO> dtoList) {
        // 解析dtoList存储到本地数据源
        System.out.println("LyErp接收到ERP同步的商品数据：");
        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(dtoList));
    }
}
