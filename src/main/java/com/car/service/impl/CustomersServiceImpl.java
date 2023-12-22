package com.car.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.car.entity.Customers;
import com.car.entity.CustomersOnProbationDTO;
import com.car.mapper.CustomersMapper;
import com.car.service.CustomersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author djh
 * @since 2023-12-16
 */
@Service
public class CustomersServiceImpl extends ServiceImpl<CustomersMapper, Customers> implements CustomersService {

    private final CustomersMapper customersMapper;

    @Autowired
    public CustomersServiceImpl(CustomersMapper customersMapper) {
        this.customersMapper = customersMapper;
    }

    @Override
    public Page<Customers> getCustomersWithPagination(int pageNum, int pageSize) {
        Page<Customers> page = new Page<>(pageNum, pageSize);
        customersMapper.selectPage(page, null);
        return page;
    }

    public List<CustomersOnProbationDTO> getCustomersOnProbation() {
        return customersMapper.getCustomersOnProbation();
    }
}
