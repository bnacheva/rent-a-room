package com.userservice.userservice.service;

import com.userservice.userservice.model.User;
import com.userservice.userservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Transactional
@AllArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Flux<User> getAll() {
        return userRepository.findAll().switchIfEmpty(Flux.empty());
    }

    public Mono<User> getById(final Long id) {
        return userRepository.findById(id);
    }

    public Mono update(final Long id, final User user) {
        return userRepository.save(user);
    }

    public Mono save(final User user) {
        return userRepository.save(user);
    }

    public Mono delete(final Long id) {
        final Mono<User> dbUser = getById(id);
        if (Objects.isNull(dbUser)) {
            return Mono.empty();
        }
        return getById(id).switchIfEmpty(Mono.empty())
                .filter(Objects::nonNull)
                .flatMap(userToBeDeleted -> userRepository
                .delete(userToBeDeleted).then(Mono.just(userToBeDeleted)));
    }
}
