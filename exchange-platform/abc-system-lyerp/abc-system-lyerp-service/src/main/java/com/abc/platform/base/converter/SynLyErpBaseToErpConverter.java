package com.abc.platform.base.converter;

import com.abc.platform.base.dal.entity.Goods;
import com.abc.platform.base.dto.SynErpGoodsFromLyErpGoodsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SynLyErpBaseToErpConverter {

    @Mappings({})
    SynErpGoodsFromLyErpGoodsDTO lyErpGoodsToErpGoodsDTO(Goods entity);

    List<SynErpGoodsFromLyErpGoodsDTO> lyErpGoodsToErpGoodsDTO(List<Goods> entityList);
}
