package com.car.entity;

import lombok.Data;

@Data
public class LocationRentalsDTO {
    private int locationId;
    private String streetAddress;
    private String city;
    private String state;
    private Long numberOfRentals;
}
