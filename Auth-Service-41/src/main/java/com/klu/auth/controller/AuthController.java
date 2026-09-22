package com.klu.auth.controller;

import com.klu.auth.entity.User;
import com.klu.auth.service.JWTService;
import com.klu.auth.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JWTService jwtService;

    public AuthController(
            UserService userService,
            JWTService jwtService) {

        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody User user) {

        try {

            if (user.getUsername() == null ||
                user.getUsername().isBlank()) {

                return ResponseEntity
                        .badRequest()
                        .body("Username is required");
            }

            if (user.getPassword() == null ||
                user.getPassword().isBlank()) {

                return ResponseEntity
                        .badRequest()
                        .body("Password is required");
            }

            User savedUser =
                    userService.registerUser(user);

            return ResponseEntity.ok(
                    Map.of(
                        "message",
                        "User registered successfully",
                        "username",
                        savedUser.getUsername(),
                        "role",
                        savedUser.getRole()
                    )
            );

        } catch (RuntimeException e) {

            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody Map<String, String> loginRequest) {

        String username =
                loginRequest.get("username");

        String password =
                loginRequest.get("password");

        User user =
                userService.findByUsername(username);

        if (user == null) {

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid credentials");
        }

        boolean validPassword =
                userService.checkPassword(
                        password,
                        user.getPassword()
                );

        if (!validPassword) {

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid credentials");
        }

        String token =
                jwtService.generateToken(
                        user.getUsername(),
                        user.getRole()
                );

        return ResponseEntity.ok(
                Map.of(
                    "message", "Login successful",
                    "token", token,
                    "username", user.getUsername(),
                    "role", user.getRole()
                )
        );
    }
}