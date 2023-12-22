package com.car.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.car.entity.Cars;
import com.baomidou.mybatisplus.extension.service.IService;
import com.car.entity.RentalTrendResultDTO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author djh
 * @since 2023-12-18
 */
public interface CarsService extends IService<Cars> {
    Page<Cars> getCarsWithPagination(int pageNum, int pageSize);
    List<Cars> getCarsByPassengers(Integer requiredPassengers);

    List<RentalTrendResultDTO> RentalTrends();

    void increaseRentalPrice(Integer carid, Double increaseAmount);
}
