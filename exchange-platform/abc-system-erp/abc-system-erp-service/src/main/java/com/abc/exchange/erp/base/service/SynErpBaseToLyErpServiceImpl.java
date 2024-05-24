package com.abc.exchange.erp.base.service;

import com.abc.exchange.erp.base.api.ISynErpBaseToLyErpService;
import com.abc.exchange.lyerp.base.api.ISynLyErpBaseFromErpService;
import com.abc.exchange.erp.base.converter.SynErpBaseToLyErpConverter;
import com.abc.exchange.erp.base.dal.entity.Goods;
import com.abc.exchange.lyerp.base.dto.SynLyErpGoodsFromErpGoodsDTO;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.ArrayList;
import java.util.List;

@DubboService
@RequiredArgsConstructor
public class SynErpBaseToLyErpServiceImpl implements ISynErpBaseToLyErpService {

    private final SynErpBaseToLyErpConverter synErpBaseToLyErpConverter;

    @DubboReference
    private ISynLyErpBaseFromErpService synLyErpBaseFromErpService;

    @Override
    public void synErpGoodsToLyErpGoods(Long goodsId) {
        // 1.当goodsId存在时，作为查询条件，否则查询所有数据（可设置同步任务功能，用于指定同步任务条数）

        List<Goods> goodsList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Goods goods = new Goods();
            goods.setId((long) (i + 1));
            goods.setCode("code-" + (i + 1));
            goods.setName("name-" + (i + 1));
            goodsList.add(goods);
        }
        // 2.封装LyERP中的GoodsDTO，调用LyERP-API
        List<SynLyErpGoodsFromErpGoodsDTO> synDTOList = synErpBaseToLyErpConverter.erpGoodsToLyErpGoodsDTO(goodsList);
        synLyErpBaseFromErpService.synLyErpGoodsFromErpGoods(synDTOList);

        // 3.回填已同步标记或最新同步时间
    }
}
