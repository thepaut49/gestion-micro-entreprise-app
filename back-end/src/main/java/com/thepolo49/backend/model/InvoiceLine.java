package com.thepolo49.backend.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "invoiceLines")
@Data
@NoArgsConstructor
public class InvoiceLine implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private InvoiceLineId invoiceLineId;

    private String description;

    private Double taxPercentage;

    private AccountingCode accountingCode;

    private Double quantity;

    private QuantityType quantityType;

    private Double amountForRefQuantity;

    @Transient
    private Double amountExcludingTax;

    @Transient
    private Double amountWithTax;

}
