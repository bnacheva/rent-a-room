package com.apigateway.apigateway.model;

import lombok.Data;

@Data
public class Room {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
