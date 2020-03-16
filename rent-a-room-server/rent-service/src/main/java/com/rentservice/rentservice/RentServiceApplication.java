package com.rentservice.rentservice;

import com.rentservice.rentservice.model.Room;
import com.rentservice.rentservice.repository.RentRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@EnableDiscoveryClient
@SpringBootApplication
public class RentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentServiceApplication.class, args);
	}

	@Bean
	ApplicationRunner init(RentRepository repository) {
		return args -> {
			Stream.of("Double room", "Apartment", "Studio").forEach(name -> {
				repository.save(new Room(name));
			});
			repository.findAll().forEach(System.out::println);
		};
	}
}
