package com.thepolo49.backend.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record InvoiceDto(
        String id,
        String invoiceType,
        String companyId,
        String personId,
        String microCompanyId,
        List<InvoiceLineDto> invoiceLines,
        Double amountExcludingTax,
        Double amountWithTax,
        LocalDate dueDate,
        LocalDate paymentDate,
        Boolean quote,
        String paymentMethod,
        Boolean payed,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt) {
}