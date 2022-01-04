package com.thepolo49.backend.dto.commons;

import java.time.LocalDateTime;

public record AddressDto (
        String id,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String addressLine1,
        String addressLine2,
        String cityName,
        String countryName,
        String postalCode) {
}