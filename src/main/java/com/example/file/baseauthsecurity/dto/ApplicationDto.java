package com.example.file.baseauthsecurity.dto;

import com.example.file.baseauthsecurity.enums.Application;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ApplicationDto {
    private Long id;
    private String name;
    private String description;
    private Long userId;
    private Application application;
}
