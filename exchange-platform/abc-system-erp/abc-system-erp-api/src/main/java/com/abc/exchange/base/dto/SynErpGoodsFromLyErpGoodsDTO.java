package com.abc.exchange.base.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SynErpGoodsFromLyErpGoodsDTO implements Serializable {
    private Long id;
    private String code;
    private String name;
}
