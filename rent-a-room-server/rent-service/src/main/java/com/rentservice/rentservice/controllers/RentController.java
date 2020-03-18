package com.rentservice.rentservice.controllers;

import com.rentservice.rentservice.model.Room;
import com.rentservice.rentservice.repository.RentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
public class RentController {

    private RentRepository rentRepository;

    public RentController(RentRepository rentRepository) {
        this.rentRepository = rentRepository;
    }

    @PostMapping("/rooms")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Room> addRoom(@RequestBody Room room) {
        return rentRepository.save(room);
    }

    @GetMapping("/rooms")
    public Flux<Room> getRoom() {
        return rentRepository.findAll();
    }

    @DeleteMapping("/rooms/{id}")
    public Mono<ResponseEntity<Void>> deleteRoom(@PathVariable("id") UUID id) {
        return rentRepository.findById(id)
                .flatMap(car -> rentRepository.delete(car)
                        .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
