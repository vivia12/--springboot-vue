package com.car.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.car.entity.Reservations;
import com.car.mapper.ReservationsMapper;
import com.car.service.ReservationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author djh
 * @since 2023-12-18
 */
@Service
public class ReservationsServiceImpl extends ServiceImpl<ReservationsMapper, Reservations> implements ReservationsService {
    private final ReservationsMapper reservationsMapper;

    @Autowired
    ReservationsServiceImpl(ReservationsMapper reservationsMapper) {
        this.reservationsMapper = reservationsMapper;
    }

    @Override
    public Page<Reservations> getReservationsWithPagination(int pageNum, int pageSize) {
        Page<Reservations> page = new Page<>(pageNum, pageSize);
        reservationsMapper.selectPage(page, null);
        return page;
    }
}

