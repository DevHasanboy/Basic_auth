package com.example.file.baseauthsecurity.impl;


import com.example.file.baseauthsecurity.dto.UserDto;
import com.example.file.baseauthsecurity.entity.Applications;
import com.example.file.baseauthsecurity.entity.User;
import com.example.file.baseauthsecurity.enums.Role;
import com.example.file.baseauthsecurity.repository.ApplicationRepository;
import com.example.file.baseauthsecurity.repository.UserRepository;
import com.example.file.baseauthsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationRepository appRepository;

    @Override
    public ResponseEntity<?> deleteUser(Long id) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(" user not found"));
        userRepository.delete(user);
        return ResponseEntity.status(HttpStatus.OK).body("user delete successfully");
    }

    public ResponseEntity<String> registerUser(String username, String password) {
        Optional<User> existingUser = userRepository.findByUsername(username);
        if (existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
        } else {
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(passwordEncoder.encode(password));
            newUser.setRole(Role.USER);
            userRepository.save(newUser);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        }
    }

    public ResponseEntity<?> getUserInfo(@RequestParam String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            User u = user.get();
            return ResponseEntity.ok("user info :" + u);
        }
        return ResponseEntity.ok("user not found");
    }

    @Override
    public ResponseEntity<?> getAll() {
        List<User> all = this.userRepository.findAll();
        if (all.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No users found");
        }
        return ResponseEntity.ok(all);
    }

    @Override
    public ResponseEntity<?> getWithAppById(Long userId) {
        User user = this.userRepository.findById(userId).
                orElse(null);
        if (user != null) {
            List<Applications> byUserId = appRepository.findByUserId(userId);
            if (!byUserId.isEmpty()) {
                final UserDto build = UserDto.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .app(byUserId)
                        .role(user.getRole())
                        .build();
                return ResponseEntity.status(HttpStatus.OK).body(build);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user not found");
    }
}



