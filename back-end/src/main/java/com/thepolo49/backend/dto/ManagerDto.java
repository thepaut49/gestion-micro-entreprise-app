package com.thepolo49.backend.dto;

import java.time.LocalDateTime;


public record ManagerDto(
        String firstName,
        String familyName,
        String email,
        String phone) {
}