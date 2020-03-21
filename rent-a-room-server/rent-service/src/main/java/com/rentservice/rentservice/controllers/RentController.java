package com.rentservice.rentservice.controllers;

import com.rentservice.rentservice.model.Room;
import com.rentservice.rentservice.service.RentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("rooms")
@AllArgsConstructor
public class RentController {

    private final RentService rentService;

    public RentController(RentService rentService) {
        this.rentService = rentService;
    }

    @GetMapping
    public Flux<Room> getAll() {
        return rentService.getAll();
    }

    @GetMapping("{id}")
    public Mono getRoom(@PathVariable("id") final UUID id) {
        return rentService.getById(id);
    }

    @PutMapping("{id}")
    public Mono updateRoom(@PathVariable("id") final UUID id, @RequestBody final Room room) {
        return rentService.update(id, room);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Room> addRoom(@RequestBody final Room room) {
        return rentService.save(room);
    }

    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Void>> deleteRoom(@PathVariable("id") UUID id) {
        return rentService.delete(id);
    }
}
