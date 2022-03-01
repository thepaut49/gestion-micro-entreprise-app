package com.thepolo49.backend.dto;

public record AccountingCodeDto(
        Integer code,
        String account,
        String coteDuBilan,
        String accountType) {
}