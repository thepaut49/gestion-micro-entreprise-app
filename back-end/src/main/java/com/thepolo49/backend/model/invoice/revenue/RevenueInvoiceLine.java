package com.thepolo49.backend.model.invoice.revenue;

import com.thepolo49.backend.model.AccountingCode;
import com.thepolo49.backend.model.invoice.QuantityType;
import lombok.*;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class RevenueInvoiceLine implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer lineNumber;

    private String description;

    private Double taxPercentage;

    @DBRef
    private AccountingCode accountingCode;

    private Double quantity;

    private QuantityType quantityType;

    private Double amountForRefQuantity;

    @Transient
    private Double amountExcludingTax;

    @Transient
    private Double amountWithTax;

}
