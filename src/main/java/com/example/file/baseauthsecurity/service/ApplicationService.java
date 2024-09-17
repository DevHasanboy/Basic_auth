package com.example.file.baseauthsecurity.service;

import com.example.file.baseauthsecurity.dto.ApplicationDto;
import org.springframework.http.ResponseEntity;

public interface ApplicationService {

    ResponseEntity<?> createApplication(ApplicationDto dto, Long userId);

    ResponseEntity<?> getApp(Long id);

    ResponseEntity<?> deleteApplication(Long id);

    ResponseEntity<?> getAllApps();

    ResponseEntity<?> updateApprovedApp(Long id);

    ResponseEntity<?> updateRejectApp(Long id);
}
