package com.thepolo49.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponseDto {
    private String id;
    private String username;
    private Set<String> authorities;
    private String token;
}
