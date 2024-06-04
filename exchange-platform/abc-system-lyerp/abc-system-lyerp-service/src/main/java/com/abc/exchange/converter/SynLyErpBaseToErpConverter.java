package com.abc.exchange.converter;

import com.abc.exchange.dal.entity.Goods;
import com.abc.exchange.base.dto.SynErpGoodsFromLyErpGoodsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SynLyErpBaseToErpConverter {

    @Mappings({})
    SynErpGoodsFromLyErpGoodsDTO lyErpGoodsToErpGoodsDTO(Goods entity);

    List<SynErpGoodsFromLyErpGoodsDTO> lyErpGoodsToErpGoodsDTO(List<Goods> entityList);
}
