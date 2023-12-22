package com.car.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.car.entity.Customers;
import com.car.entity.CustomersOnProbationDTO;
import com.car.service.CustomersService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomersController {

    private final CustomersService customersService;

    @Autowired
    CustomersController(CustomersService customersService) {
        this.customersService = customersService;
    }

    // 分页查询客户
    @GetMapping("/page")
    public ResponseEntity<Page<Customers>> getCustomersWithPagination(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        Page<Customers> page = customersService.getCustomersWithPagination(pageNum, pageSize);
        return ResponseEntity.ok(page);
    }

    // 创建客户
    @PostMapping
    public ResponseEntity<Customers> createCustomer(@RequestBody Customers customer) {
        customersService.save(customer);
        return ResponseEntity.ok(customer);
    }

// 更新客户
    @PutMapping("/{id}")
    public ResponseEntity<Customers> updateCustomer(@PathVariable Integer id, @RequestBody Customers customerDetails) {
        customerDetails.setCustomerid(id); // Set the ID from the path variable
        customersService.updateById(customerDetails);
        return ResponseEntity.ok(customerDetails);
    }


    // 删除客户
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        customersService.removeById(id);
        return ResponseEntity.ok().build();
    }

    // 获取所有客户
    @GetMapping("/all")
    public ResponseEntity<List<Customers>> getAllCustomers() {
        List<Customers> customers = customersService.list();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/onProbation")
    public ResponseEntity<List<CustomersOnProbationDTO>> getCustomersOnProbation() {
        List<CustomersOnProbationDTO> customers = customersService.getCustomersOnProbation();
        return ResponseEntity.ok(customers);
    }
}
