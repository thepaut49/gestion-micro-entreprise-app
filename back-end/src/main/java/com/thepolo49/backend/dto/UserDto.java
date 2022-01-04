package com.thepolo49.backend.dto;

import java.time.LocalDateTime;
import java.util.Set;


public record UserDto(
        String id,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        boolean enabled,
        String username,
        String password,
        Set<String> authorities
) {

}
