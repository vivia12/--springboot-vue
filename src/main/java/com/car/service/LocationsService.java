package com.car.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.car.entity.LocationRentalsDTO;
import com.car.entity.Locations;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author djh
 * @since 2023-12-18
 */
public interface LocationsService extends IService<Locations> {
    Page<Locations> getLocationsWithPagination(int pageNum, int pageSize);
     List<LocationRentalsDTO> PopularLocations();
}
