package com.abc.exchange.business.dal.entity;

//import com.baomidou.mybatisplus.annotation.TableField;
//import com.baomidou.mybatisplus.annotation.TableId;
//import com.baomidou.mybatisplus.annotation.TableName;
//import lombok.Data;
//
//@Data
//@TableName("group_sdb_ord")
//public class GroupSdbOrd {
//    @TableId
//    private Long id;
//
//    @TableField("bill_no")
//    private String billNo;
//}




import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "group_sdb_ord")
public class GroupSdbOrd {
    @Id
    private Long id;

    @Column(name = "bill_no")
    private String billNo;
}
