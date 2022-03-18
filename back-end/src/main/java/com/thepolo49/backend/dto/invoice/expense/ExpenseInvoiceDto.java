package com.thepolo49.backend.dto.invoice.expense;

import com.thepolo49.backend.dto.MicroCompanyDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record ExpenseInvoiceDto (
        String id,
        String externalRef,
        MicroCompanyDto microCompany,
        SupplierDto supplier,
        List<ExpenseInvoiceLineDto> invoiceLines,
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
