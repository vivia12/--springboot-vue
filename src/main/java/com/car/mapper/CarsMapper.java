package com.car.mapper;

import com.car.entity.Cars;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.car.entity.RentalTrendResultDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author djh
 * @since 2023-12-18
 */
public interface CarsMapper extends BaseMapper<Cars> {
    @Select("SELECT * FROM getCarsByPassengers(#{requiredPassengers})")
    List<Cars> getCarsByPassengers(@Param("requiredPassengers") Integer requiredPassengers);

    @Select("SELECT * FROM rental_trends()")
    List<RentalTrendResultDTO> RentalTrends();

    @Update("UPDATE Cars SET rentalprice = rentalprice + #{increaseAmount} WHERE carid = #{carid}")
    void increaseRentalPrice(@Param("carid") Integer carid, @Param("increaseAmount") Double increaseAmount);

}
