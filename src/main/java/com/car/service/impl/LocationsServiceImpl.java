package com.car.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.car.entity.LocationRentalsDTO;
import com.car.entity.Locations;
import com.car.mapper.LocationsMapper;
import com.car.service.LocationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author djh
 * @since 2023-12-18
 */
@Service
public class LocationsServiceImpl extends ServiceImpl<LocationsMapper, Locations> implements LocationsService {
    private final LocationsMapper locationsMapper;

    @Autowired
    LocationsServiceImpl(LocationsMapper locationsMapper) {
        this.locationsMapper = locationsMapper;
    }

    @Override
    public Page<Locations> getLocationsWithPagination(int pageNum, int pageSize) {
        Page<Locations> page = new Page<>(pageNum, pageSize);
        baseMapper.selectPage(page, null);
        return page;
    }

    @Override
    public List<LocationRentalsDTO> PopularLocations() {
        return locationsMapper.callPopularLocationsFunction();
    }
}
