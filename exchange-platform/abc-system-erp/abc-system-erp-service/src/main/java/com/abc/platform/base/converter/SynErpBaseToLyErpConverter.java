package com.abc.platform.base.converter;

import com.abc.platform.base.dal.entity.Goods;
import com.abc.platform.base.dto.SynLyErpGoodsFromErpGoodsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SynErpBaseToLyErpConverter {

    @Mappings({})
    SynLyErpGoodsFromErpGoodsDTO erpGoodsToLyErpGoodsDTO(Goods entity);

    List<SynLyErpGoodsFromErpGoodsDTO> erpGoodsToLyErpGoodsDTO(List<Goods> entityList);
}
