package com.thepolo49.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class UserDto {

    private String id;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private  boolean enabled;
    private String username;
    private String password;
    private Set<String> authorities;
    private String token;


}
