package com.example.file.baseauthsecurity.controller;

import com.example.file.baseauthsecurity.dto.ApplicationDto;
import com.example.file.baseauthsecurity.service.ApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/app")
@RequiredArgsConstructor
@Tag(name = "application")
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping("/create/{userId}")
    public ResponseEntity<?> createApplication(@RequestBody ApplicationDto application, @PathVariable Long userId) {
        ResponseEntity response = this.applicationService.createApplication(application, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getApp(@PathVariable Long id) {
        ResponseEntity response = this.applicationService.getApp(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<?> deleteApp(@PathVariable Long id) {
        ResponseEntity response = this.applicationService.deleteApplication(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/get_all")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<?> getAllApp() {
        ResponseEntity response = this.applicationService.getAllApps();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/approve/{id}")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<?> approvedApp(@PathVariable Long id) {
        ResponseEntity response = this.applicationService.updateApprovedApp(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/reject/{id}")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<?> rejectApp(@PathVariable Long id) {
        ResponseEntity response = this.applicationService.updateRejectApp(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
