package com.abc.platform.base.dal.entity;

//import com.baomidou.mybatisplus.annotation.TableField;
//import com.baomidou.mybatisplus.annotation.TableId;
//import com.baomidou.mybatisplus.annotation.TableName;
//import lombok.Data;
//
//
//@Data
//@TableName("group_pdm_goods")
//public class Goods {
//    @TableId
//    private Long id;
//
//    @TableField("code")
//    private String code;
//
//    @TableField("name")
//    private String name;
//}


import com.abc.platform.util.AbcTkGlobalIdGen;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "group_pdm_goods")
public class Goods {
    @Id
    @KeySql(genId = AbcTkGlobalIdGen.class)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;
}
