package com.thepolo49.backend.dto.commons;

import com.googlecode.jmapper.annotations.JGlobalMap;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;


@JGlobalMap
public record ContactInfoDto(
        String id,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String phone,
        String email) {
}