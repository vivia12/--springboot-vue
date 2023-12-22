package com.car.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Reservations对象", description="")
public class Reservations implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "reservationid", type = IdType.AUTO)
    private Integer reservationid;

    private Integer customerid;

    private Integer carid;

    private Integer pickuplocationid;

    private Integer dropofflocationid;

    private Date startdate;

    private Date enddate;

    private BigDecimal totalcost;

    private String reservationstatus;

    // 非数据库字段，用于展示关联的用户、车辆和地点信息
    @TableField(exist = false)
    private Customers customer;
    @TableField(exist = false)
    private Cars car;
    @TableField(exist = false)
    private Locations pickupLocation;
    @TableField(exist = false)
    private Locations dropoffLocation;

}
