package com.thepolo49.backend.dto.invoice.revenue;

import com.thepolo49.backend.dto.AccountingCodeDto;
import com.thepolo49.backend.model.invoice.QuantityType;

public record RevenueInvoiceLineDto(
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
