package com.abc.exchange.util;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import tk.mybatis.mapper.genid.GenId;

public class AbcTkGlobalIdGen implements GenId<Long> {
    @Override
    public Long genId(String table, String column) {
        // 使用Mybatis-plus的SnowFlakeID生成器
        // 你可以在此替换自定义的SnowFlakeID生成器, 可参考doc目录下的示例封装
        return IdWorker.getId();
    }
}
