package com.apigateway.apigateway.service;

import com.apigateway.apigateway.model.Room;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "rooms")
public interface RoomClient {

    @GetMapping("/rooms")
    @CrossOrigin
    CollectionModel<Room> readRooms();
}
