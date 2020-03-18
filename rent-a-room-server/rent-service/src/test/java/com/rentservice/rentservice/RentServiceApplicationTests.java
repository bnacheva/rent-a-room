package com.rentservice.rentservice;

import com.rentservice.rentservice.model.Room;
import com.rentservice.rentservice.repository.RentRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
				properties = {"spring.cloud.discovery.enabled = false"})
class RentServiceApplicationTests {

	RentRepository rentRepository;

	WebTestClient webTestClient;

	@Test
	public void testAddRoom() {
		Room room = new Room(UUID.randomUUID(), "Apartment", "Apartment", "Apartment");

		webTestClient.post().uri("/rooms")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.body(Mono.just(room), Room.class)
				.exchange()
				.expectStatus().isCreated()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody()
				.jsonPath("$.id").isNotEmpty()
				.jsonPath("$.name").isEqualTo("Apartment");
	}

	@Test
	public void testGetAllRooms() {
		webTestClient.get().uri("/rooms")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBodyList(Room.class);
	}

	@Test
	public void testDeleteRoom() {
		Room room = rentRepository.save(new Room(UUID.randomUUID(), "Apartment", "Apartment", "Apartment"))
				.block();

		webTestClient.delete()
				.uri("/rooms/{id}", Collections.singletonMap("id", room.getId()))
				.exchange()
				.expectStatus().isOk();
	}
}
