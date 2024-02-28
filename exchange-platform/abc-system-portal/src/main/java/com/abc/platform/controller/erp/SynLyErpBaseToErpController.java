package com.abc.platform.controller.erp;

import com.abc.platform.base.api.ISynLyErpBaseToErpService;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lyErp/to/erp")
@RequiredArgsConstructor
public class SynLyErpBaseToErpController {

    @DubboReference
    private ISynLyErpBaseToErpService synLyErpBaseToErpService;

    @PostMapping("/goods")
    public void SynErpGoodsToLyErpGoods() {
        synLyErpBaseToErpService.synLyErpGoodsToErpGoods(null);
    }
}
