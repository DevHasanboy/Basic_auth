package com.example.file.baseauthsecurity.dto;

import com.example.file.baseauthsecurity.entity.Applications;
import com.example.file.baseauthsecurity.enums.Role;
import lombok.*;

import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private Role role;
    private List<Applications> app;

}
