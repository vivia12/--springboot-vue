package com.car.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.car.entity.Customers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.car.entity.CustomersOnProbationDTO;
import com.car.entity.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author djh
 * @since 2023-12-16
 */
public interface CustomersService extends IService<Customers> {
    Page<Customers> getCustomersWithPagination(int pageNum, int pageSize);

   // Customers updateUser(Long id, Customers CustomersDetails);
    List<CustomersOnProbationDTO> getCustomersOnProbation();
}
