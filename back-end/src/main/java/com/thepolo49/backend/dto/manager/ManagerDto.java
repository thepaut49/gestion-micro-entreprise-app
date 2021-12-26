package com.thepolo49.backend.dto.manager;

import com.googlecode.jmapper.annotations.JGlobalMap;
import com.thepolo49.backend.dto.commons.ContactInfoDto;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;


@JGlobalMap
public record ManagerDto(
        String id,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String firstName,
        String familyName,
        ContactInfoDto contactInfo) {
}