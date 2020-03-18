package com.rentservice.rentservice;

import com.rentservice.rentservice.model.Room;
import com.rentservice.rentservice.repository.RentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

import java.util.Set;
import java.util.UUID;

@EnableDiscoveryClient
@SpringBootApplication
@Slf4j
public class RentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentServiceApplication.class, args);
	}

	@Bean
	ApplicationRunner init(RentRepository repository) {
		Room room1 = new Room(UUID.randomUUID(), "Apartment", "Apartment", "Apartment");
		Room room2 = new Room(UUID.randomUUID(), "Studio", "Studio", "Studio");
		Room room3 = new Room(UUID.randomUUID(), "Studio", "Studio", "Studio");
		Room room4 = new Room(UUID.randomUUID(), "Double room", "Double room", "Double room");
		Set<Room> rooms = Set.of(room1, room2, room3, room4);

		return args -> {
			repository
					.deleteAll()
					.thenMany(
							Flux
									.just(rooms)
									.flatMap(repository::saveAll)
					)
					.thenMany(repository.findAll());
		};
	}
}
