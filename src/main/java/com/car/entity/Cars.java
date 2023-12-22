package com.car.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2023-12-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Cars对象", description="")
public class Cars implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "carid", type = IdType.AUTO)
    private Integer carid;

    private String brand;

    private String model;

    private BigDecimal rentalprice;

    private Integer passengercapacity;


}
