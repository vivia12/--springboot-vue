package com.car.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.car.entity.Reservations;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author djh
 * @since 2023-12-18
 */
public interface ReservationsService extends IService<Reservations> {
   Page<Reservations> getReservationsWithPagination(int pageNum, int pageSize);
}
