package com.example.file.baseauthsecurity.controller;


import com.example.file.baseauthsecurity.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
@Tag(name = "user")
public class UserController {

    private final UserService userService;

    @GetMapping("/user/{username}")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<?> getUserInfo(@PathVariable String username) {
        return userService.getUserInfo(username);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestParam String username,
                                               @RequestParam String password) {
        return userService.registerUser(username, password);
    }

    @GetMapping("/get_app_with_userId/{userId}")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<?> getAppWithUserId(@PathVariable Long userId) {
        ResponseEntity response = this.userService.getWithAppById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/get_all")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<?> getAllUsers() {
        return userService.getAll();
    }

    @PutMapping("/delete/{id}")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        ResponseEntity response = this.userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
