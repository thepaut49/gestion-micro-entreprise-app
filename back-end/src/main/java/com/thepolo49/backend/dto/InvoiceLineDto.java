package com.thepolo49.backend.dto;

public record InvoiceLineDto(
        String invoiceId,
        Integer lineNumber,
        String description,
        Double taxPercentage,
        AccountingCodeDto accountingCode,
        Double quantity,
        String quantityType,
        Double amountForRefQuantity,
        Double amountExcludingTax,
        Double amountWithTax) {
}