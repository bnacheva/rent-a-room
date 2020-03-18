package com.rentservice.rentservice.repository;

import com.rentservice.rentservice.model.Room;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.UUID;

public interface RentRepository extends ReactiveMongoRepository<Room, UUID> {
}
