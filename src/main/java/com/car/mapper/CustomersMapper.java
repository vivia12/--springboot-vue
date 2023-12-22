package com.car.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.car.entity.Customers;
import com.car.entity.CustomersOnProbationDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author djh
 * @since 2023-12-16
 */
@Mapper
public interface CustomersMapper extends BaseMapper<Customers> {
    //获取三年内有违法记录的学生
    @Select("SELECT * FROM customers_on_probation()")
    List<CustomersOnProbationDTO> getCustomersOnProbation();
}
