package com.thepolo49.backend.dto.invoice.expense;

import com.thepolo49.backend.dto.AccountingCodeDto;
import com.thepolo49.backend.model.invoice.QuantityType;

public record ExpenseInvoiceLineDto(
        Integer lineNumber,
        String description,
        Double taxPercentage,
        AccountingCodeDto accountingCode,
        Double quantity,
        QuantityType quantityType,
        Double amountForRefQuantity,
        Double amountExcludingTax,
        Double amountWithTax) {
}
