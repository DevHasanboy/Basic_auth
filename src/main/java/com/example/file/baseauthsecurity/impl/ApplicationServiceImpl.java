package com.example.file.baseauthsecurity.impl;

import com.example.file.baseauthsecurity.dto.ApplicationDto;
import com.example.file.baseauthsecurity.entity.Applications;
import com.example.file.baseauthsecurity.entity.User;
import com.example.file.baseauthsecurity.enums.Application;
import com.example.file.baseauthsecurity.repository.ApplicationRepository;
import com.example.file.baseauthsecurity.repository.UserRepository;
import com.example.file.baseauthsecurity.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationRepository repository;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<?> createApplication(ApplicationDto dto, Long userId) {
        List<Applications> list = new ArrayList<>();
        Applications applications = Applications.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .application(Application.PENDING)
                .build();
        User user = userRepository.findById(userId).
                orElse(null);
        if (user != null) {
            applications.setUserId(userId);
            list.add(applications);
            user.setApp(list);
            repository.save(applications);
            userRepository.save(user);
            return ResponseEntity.ok(applications);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user is not found");
    }

    @Override
    public ResponseEntity<?> getApp(Long id) {
        Applications applications = repository.findById(id)
                .orElse(null);
        if (applications != null) {
            ApplicationDto dto = new ApplicationDto(
                    applications.getId(),
                    applications.getName(),
                    applications.getDescription(),
                    applications.getUserId(),
                    applications.getApplication()
            );

            return ResponseEntity.status(HttpStatus.OK).body(dto);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(" application not found");

    }

    @Override
    public ResponseEntity<?> deleteApplication(Long id) {
        Optional<Applications> byId = this.repository.findById(id);
        if (byId.isPresent()) {
            final Applications applications = byId.get();
            repository.delete(applications);
            return ResponseEntity.status(HttpStatus.OK).body("applicant delete successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("applicant id not found");
    }

    @Override
    public ResponseEntity<?> getAllApps() {
        List<Applications> all = this.repository.findAll();
        if (!all.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(all);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("list is empty");
    }

    @Override
    public ResponseEntity<?> updateApprovedApp(Long id) {
        Applications applications = this.repository.findById(id)
                .orElseThrow(() -> new RuntimeException("applicant not found"));
        applications.setApplication(Application.APPROVED);
        return ResponseEntity.status(HttpStatus.OK).body(applications);
    }

    @Override
    public ResponseEntity<?> updateRejectApp(Long id) {
        Applications applications = this.repository.findById(id)
                .orElseThrow(() -> new RuntimeException("applicant not found"));
        applications.setApplication(Application.REJECTED);
        return ResponseEntity.status(HttpStatus.OK).body(applications);
    }
}
