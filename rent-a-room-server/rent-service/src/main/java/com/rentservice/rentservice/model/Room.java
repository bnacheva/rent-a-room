package com.rentservice.rentservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
public class Room {

    public Room(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    private String type;

    private String description;
}
