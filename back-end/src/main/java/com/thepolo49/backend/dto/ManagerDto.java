package com.thepolo49.backend.dto;

import java.time.LocalDateTime;


public record ManagerDto(
        String id,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String firstName,
        String familyName,
        String email,
        String phone) {
}