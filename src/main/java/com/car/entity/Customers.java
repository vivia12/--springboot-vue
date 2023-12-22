package com.car.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author djh
 * @since 2023-12-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Customers对象", description="")
public class Customers implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "客户ID，自增主键")
    @TableId(value = "customerid", type = IdType.AUTO)
    private Integer customerid;

    @ApiModelProperty(value = "客户全名")
    private String fullname;

    @ApiModelProperty(value = "客户地址")
    private String address;

    @ApiModelProperty(value = "电话号码")
    private String phonenumber;

    @ApiModelProperty(value = "移动电话号码")
    private String mobilephonenumber;

    @ApiModelProperty(value = "电子邮件地址")
    private String email;

    @ApiModelProperty(value = "信用卡号码")
    private String creditcardnumber;

    @ApiModelProperty(value = "驾驶证号码")
    private String drivinglicensenumber;

    @ApiModelProperty(value = "驾驶证发放州")
    private String licenseissuestate;

    @ApiModelProperty(value = "驾驶证到期日期")
    private Date licenseexpirationdate;

    @ApiModelProperty(value = "是否是学生，布尔值")
    private Boolean isstudent;

    @ApiModelProperty(value = "违规行为状态，比如缓刑")
    private String violationstatus;


}
