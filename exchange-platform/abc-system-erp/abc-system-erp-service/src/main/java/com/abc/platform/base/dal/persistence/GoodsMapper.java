package com.abc.platform.base.dal.persistence;

import com.abc.platform.base.dal.entity.Goods;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface GoodsMapper extends MySqlMapper<Goods>, Mapper<Goods> {
}
