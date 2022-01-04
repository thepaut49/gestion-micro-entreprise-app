package com.thepolo49.backend.dto;

import com.thepolo49.backend.dto.commons.AddressDto;

import java.time.LocalDateTime;


public record PersonDto(
        String id,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String firstName,
        String familyName,
        AddressDto address,
        String email,
        String phone) {
}