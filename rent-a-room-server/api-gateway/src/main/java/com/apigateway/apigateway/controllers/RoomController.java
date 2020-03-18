package com.apigateway.apigateway.controllers;

import com.apigateway.apigateway.model.Room;
import com.apigateway.apigateway.service.RoomClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
public class RoomController {

    private final RoomClient roomClient;

    public RoomController(RoomClient roomClient) {
        this.roomClient = roomClient;
    }

    private Collection<Room> fallback() {
        return new ArrayList<>();
    }

    @GetMapping(value = "/available-rooms", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    @HystrixCommand(fallbackMethod = "fallback")
    public Collection<Room> availableRooms() {
        return roomClient.readRooms()
                .getContent()
                .stream()
                .filter(this::isAvailable)
                .collect(Collectors.toList());
    }

    private boolean isAvailable(Room room) {
        return room.getName().equals("Double room") ||
                room.getName().equals("Apartment") ||
                room.getName().equals("Studio");
    }
}
