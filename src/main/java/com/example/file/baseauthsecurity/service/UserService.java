package com.example.file.baseauthsecurity.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserService {
    ResponseEntity<?> deleteUser(Long id);

    ResponseEntity<String> registerUser(String username, String password);

    ResponseEntity<?> getUserInfo(@RequestParam String username);

    ResponseEntity<?> getAll();

    ResponseEntity<?> getWithAppById(Long userId);
}
