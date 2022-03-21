package com.thepolo49.backend.dto.commons;

public record AddressDto (
        String addressLine1,
        String addressLine2,
        String cityName,
        String countryName,
        String postalCode) {
}