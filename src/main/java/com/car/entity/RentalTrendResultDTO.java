package com.car.entity;

import lombok.Data;

@Data
public class RentalTrendResultDTO {
    private String make;
    private String model;
    private boolean isstudent;
    private Long timesRented;
}