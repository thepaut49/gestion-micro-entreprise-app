package com.thepolo49.backend.model.invoice.expense;

import com.thepolo49.backend.model.AccountingCode;
import com.thepolo49.backend.model.invoice.QuantityType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "expense-invoices-lines")
@Data
@NoArgsConstructor
public class ExpenseInvoiceLine implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private ExpenseInvoiceLineId invoiceLineId;

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
