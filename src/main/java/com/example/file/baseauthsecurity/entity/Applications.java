package com.example.file.baseauthsecurity.entity;

import com.example.file.baseauthsecurity.enums.Application;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "application")
@ToString
@Builder
public class Applications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Long  userId;
    @Enumerated(EnumType.STRING)
    private Application application;
}
