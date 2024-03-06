package com.abc.platform.business.dal.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("group_sdb_ord")
public class GroupSdbOrd {
    @TableId("id")
    private Long id;

    @TableField("bill_no")
    private String billNo;
}
