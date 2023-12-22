package com.car.entity;

import lombok.Data;

@Data
public class TokenObject {
    private String token;

    public TokenObject(String token) {
        this.token = token;
    }

}