package com.abc.exchange.lyerp.base.converter;

import com.abc.exchange.lyerp.base.dal.entity.Goods;
import com.abc.exchange.erp.base.dto.SynErpGoodsFromLyErpGoodsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SynLyErpBaseToErpConverter {

    @Mappings({})
    SynErpGoodsFromLyErpGoodsDTO lyErpGoodsToErpGoodsDTO(Goods entity);

    List<SynErpGoodsFromLyErpGoodsDTO> lyErpGoodsToErpGoodsDTO(List<Goods> entityList);
}
