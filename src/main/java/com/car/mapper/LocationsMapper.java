package com.car.mapper;

import com.car.entity.LocationRentalsDTO;
import com.car.entity.Locations;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author djh
 * @since 2023-12-18
 */
public interface LocationsMapper extends BaseMapper<Locations> {
    @Select("SELECT * FROM PopularLocations()")
    List<LocationRentalsDTO> callPopularLocationsFunction();
}
