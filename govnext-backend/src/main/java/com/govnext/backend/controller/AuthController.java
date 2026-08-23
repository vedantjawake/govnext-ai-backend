package com.govnext.backend.controller;

import com.govnext.backend.dto.AuthDto;
import com.govnext.backend.entity.User;
import com.govnext.backend.repository.UserRepository;
import com.govnext.backend.security.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider tokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthDto.RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email is already registered!");
        }

        User user = new User(request.getName(), request.getEmail(), passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);

        String token = tokenProvider.generateToken(user.getEmail());
        return ResponseEntity.ok(new AuthDto.AuthResponse(token, user.getEmail(), user.getName()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthDto.LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        String token = tokenProvider.generateToken(user.getEmail());
        return ResponseEntity.ok(new AuthDto.AuthResponse(token, user.getEmail(), user.getName()));
    }
}