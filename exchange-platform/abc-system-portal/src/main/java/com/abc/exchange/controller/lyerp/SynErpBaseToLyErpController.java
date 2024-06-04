package com.abc.exchange.controller.lyerp;

import com.abc.exchange.base.api.ISynErpBaseToLyErpService;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/erp/to/lyErp")
@RequiredArgsConstructor
public class SynErpBaseToLyErpController {

    @DubboReference
    private ISynErpBaseToLyErpService synErpBaseToLyErpService;

    @PostMapping("/goods")
    public void SynErpGoodsToLyErpGoods() {
        synErpBaseToLyErpService.synErpGoodsToLyErpGoods(null);
    }
}
