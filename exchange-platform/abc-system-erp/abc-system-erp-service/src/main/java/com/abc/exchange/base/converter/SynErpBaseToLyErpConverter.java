package com.abc.exchange.base.converter;

import com.abc.exchange.base.dal.entity.Goods;
import com.abc.exchange.lyerp.base.dto.SynLyErpGoodsFromErpGoodsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SynErpBaseToLyErpConverter {

    @Mappings({})
    SynLyErpGoodsFromErpGoodsDTO erpGoodsToLyErpGoodsDTO(Goods entity);

    List<SynLyErpGoodsFromErpGoodsDTO> erpGoodsToLyErpGoodsDTO(List<Goods> entityList);
}
