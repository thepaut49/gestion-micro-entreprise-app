package com.thepolo49.backend.dto.person;

import com.googlecode.jmapper.annotations.JGlobalMap;
import com.thepolo49.backend.dto.commons.AddressDto;
import com.thepolo49.backend.dto.commons.ContactInfoDto;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;


@JGlobalMap
public record PersonDto(
        String id,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String firstName,
        String familyName,
        AddressDto address,
        ContactInfoDto contactInfo) {
}