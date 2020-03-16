package com.rentservice.rentservice.repository;

import com.rentservice.rentservice.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentRepository extends JpaRepository<Room, Long> {
}
