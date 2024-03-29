package com.abc.platform.base.dal.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("group_pdm_goods")
public class Goods {
    @TableId("id")
    private Long id;

    @TableField("code")
    private String code;

    @TableField("name")
    private String name;
}
