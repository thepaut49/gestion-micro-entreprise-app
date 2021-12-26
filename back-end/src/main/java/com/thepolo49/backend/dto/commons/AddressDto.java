package com.thepolo49.backend.dto.commons;

import com.googlecode.jmapper.annotations.JGlobalMap;
import org.bson.types.ObjectId;
import java.time.LocalDateTime;


@JGlobalMap
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