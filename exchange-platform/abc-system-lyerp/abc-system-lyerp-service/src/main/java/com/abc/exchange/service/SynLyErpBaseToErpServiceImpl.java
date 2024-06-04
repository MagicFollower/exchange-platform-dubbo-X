package com.abc.exchange.service;

import com.abc.exchange.base.api.ISynErpBaseFromLyErpService;
import com.abc.exchange.dal.entity.Goods;
import com.abc.exchange.lyerp.base.api.ISynLyErpBaseToErpService;
import com.abc.exchange.converter.SynLyErpBaseToErpConverter;
import com.abc.exchange.base.dto.SynErpGoodsFromLyErpGoodsDTO;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.ArrayList;
import java.util.List;

@DubboService
@RequiredArgsConstructor
public class SynLyErpBaseToErpServiceImpl implements ISynLyErpBaseToErpService {

    private final SynLyErpBaseToErpConverter synLyErpBaseToErpConverter;

    @DubboReference
    private ISynErpBaseFromLyErpService synErpBaseFromLyErpService;

    @Override
    public void synLyErpGoodsToErpGoods(Long goodsId) {
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
        List<SynErpGoodsFromLyErpGoodsDTO> synDTOList = synLyErpBaseToErpConverter.lyErpGoodsToErpGoodsDTO(goodsList);
        synErpBaseFromLyErpService.synErpGoodsFromLyErpGoods(synDTOList);

        // 3.回填已同步标记或最新同步时间
    }
}
