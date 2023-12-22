package com.car.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.car.entity.Cars;
import com.car.entity.RentalTrendResultDTO;
import com.car.mapper.CarsMapper;
import com.car.service.CarsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author djh
 * @since 2023-12-18
 */
@Service
public class CarsServiceImpl extends ServiceImpl<CarsMapper, Cars> implements CarsService {
    @Autowired
    private CarsMapper carsMapper;
    @Override
    public Page<Cars> getCarsWithPagination(int pageNum, int pageSize) {
        Page<Cars> page = new Page<>(pageNum, pageSize);
        this.page(page, null);
        return page;
    }
    @Override
    public List<Cars> getCarsByPassengers(Integer requiredPassengers) {
        return carsMapper.getCarsByPassengers(requiredPassengers);
    }

    @Override
    public List<RentalTrendResultDTO> RentalTrends(){
        return carsMapper.RentalTrends();
    }

    @Override
    public void increaseRentalPrice(Integer carid, Double increaseAmount) {
        carsMapper.increaseRentalPrice(carid, increaseAmount);
    }

}
