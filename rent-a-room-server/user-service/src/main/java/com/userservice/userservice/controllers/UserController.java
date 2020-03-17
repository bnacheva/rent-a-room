package com.userservice.userservice.controllers;

import com.userservice.userservice.model.User;
import com.userservice.userservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping("user")
@AllArgsConstructor
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Flux<User> getAll() {
        return userService.getAll();
    }


    @GetMapping("{id}")
    public Mono<User> getById(@PathVariable("id") final Long id) {
        return userService.getById(id);
    }

    @PutMapping("{id}")
    public Mono updateById(@PathVariable("id") final Long id, @RequestBody final User user) {
        return userService.update(id, user);
    }

    @PostMapping
    public Mono save(@RequestBody final User user) {
        return userService.save(user);
    }

    @DeleteMapping("{id}")
    public Mono delete(@PathVariable final Long id) {
        return userService.delete(id);
    }
}
