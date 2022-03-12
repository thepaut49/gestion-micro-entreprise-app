package com.thepolo49.backend.dto.invoice.revenue;

import com.thepolo49.backend.dto.MicroCompanyDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record RevenueInvoiceDto(
        String id,
        MicroCompanyDto microCompany,
        ClientDto client,
        List<RevenueInvoiceLineDto> invoiceLines,
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
