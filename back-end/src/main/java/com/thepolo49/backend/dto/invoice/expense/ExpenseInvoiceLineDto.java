package com.thepolo49.backend.dto.invoice.expense;

import com.thepolo49.backend.model.AccountingCode;
import com.thepolo49.backend.model.invoice.QuantityType;

public record ExpenseInvoiceLineDto(
        String invoiceId,
        Integer lineNumber,
        String description,
        Double taxPercentage,
        AccountingCode accountingCode,
        Double quantity,
        QuantityType quantityType,
        Double amountForRefQuantity,
        Double amountExcludingTax,
        Double amountWithTax) {
}
