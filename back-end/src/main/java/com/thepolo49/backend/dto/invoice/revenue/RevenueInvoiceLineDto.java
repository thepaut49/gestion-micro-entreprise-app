package com.thepolo49.backend.dto.invoice.revenue;

import com.thepolo49.backend.model.AccountingCode;
import com.thepolo49.backend.model.invoice.QuantityType;

public record RevenueInvoiceLineDto(
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
