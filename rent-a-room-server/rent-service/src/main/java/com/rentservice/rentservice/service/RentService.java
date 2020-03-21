package com.rentservice.rentservice.service;

import com.rentservice.rentservice.model.Room;
import com.rentservice.rentservice.repository.RentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class RentService {

    private final RentRepository rentRepository;

    public RentService(RentRepository rentRepository) {
        this.rentRepository = rentRepository;
    }

    public Flux<Room> getAll() {
        return rentRepository.findAll().switchIfEmpty(Flux.empty());
    }

    public Mono<Room> getById(final UUID id) {
        return rentRepository.findById(id);
    }

    public Mono update(final UUID id, final Room room) {
        return rentRepository.save(room);
    }

    public Mono save(final Room room) {
        return rentRepository.save(room);
    }

    public Mono delete(final UUID id) {
        final Mono<Room> dbRoom = getById(id);
        if(Objects.isNull(dbRoom)) {
            return Mono.empty();
        }
        return getById(id)
                .switchIfEmpty(Mono.empty())
                .filter(Objects::nonNull)
                .flatMap(roomToBeDeleted -> rentRepository.delete(roomToBeDeleted)
                .then(Mono.just(roomToBeDeleted)));
    }
}
