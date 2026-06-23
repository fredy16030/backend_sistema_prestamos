package com.sistema.prestamos.infrastructure.api.controller;

import com.sistema.prestamos.domain.model.User;
import com.sistema.prestamos.domain.service.UserService;
import com.sistema.prestamos.infrastructure.api.dto.UserRequestDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public Mono<ResponseEntity<User>> createUser(@Valid @RequestBody UserRequestDTO request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());
        return Mono.just(ResponseEntity.ok(userService.createUser(user)));
    }

    @GetMapping
    public Flux<User> getAllUsers() {
        return Flux.fromIterable(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<User>> getUserById(@PathVariable Long id) {
        return Mono.just(ResponseEntity.ok(userService.getUserById(id)));
    }
}
