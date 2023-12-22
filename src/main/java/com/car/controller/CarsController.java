package com.car.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.car.entity.Cars;
import com.car.entity.RentalTrendResultDTO;
import com.car.service.CarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarsController {

    private final CarsService carsService;

    @Autowired
    public CarsController(CarsService carsService) {
        this.carsService = carsService;
    }

    // 分页查询车辆
    @GetMapping("/page")
    public ResponseEntity<Page<Cars>> getCarsWithPagination(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        Page<Cars> page = carsService.getCarsWithPagination(pageNum, pageSize);
        return ResponseEntity.ok(page);
    }

    // 创建车辆
    @PostMapping
    public ResponseEntity<Cars> createCar(@RequestBody Cars car) {
        carsService.save(car);
        return ResponseEntity.ok(car);
    }

    // 更新车辆
    @PutMapping("/{id}")
    public ResponseEntity<Cars> updateCar(@PathVariable Integer id, @RequestBody Cars carDetails) {
        carDetails.setCarid(id); // Set the ID from the path variable
        carsService.updateById(carDetails);
        return ResponseEntity.ok(carDetails);
    }

    // 删除车辆
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable Integer id) {
        carsService.removeById(id);
        return ResponseEntity.ok().build();
    }

    // 获取所有车辆
    @GetMapping("/all")
    public ResponseEntity<List<Cars>> getAllCars() {
        List<Cars> cars = carsService.list();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/by-passengers")
    public ResponseEntity<List<Cars>> getCarsByPassengers(@RequestParam int requiredPassengers) {
        List<Cars> cars = carsService.getCarsByPassengers(requiredPassengers);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/trend")
    public ResponseEntity<List<RentalTrendResultDTO>> getCarRentalsTrend() {
        List<RentalTrendResultDTO> trends = carsService.RentalTrends();
        return ResponseEntity.ok(trends);
    }

    @PostMapping("/increase")
    public ResponseEntity<String> increaseRentalPrice ( @RequestParam("carid") int carid,
    @RequestParam("increaseAmount") double increaseAmount) {
        carsService.increaseRentalPrice(carid, increaseAmount);
        return ResponseEntity.ok("Rental price increased successfully.");
    }
}
