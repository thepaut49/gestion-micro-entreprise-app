package com.thepolo49.backend.model.invoice.expense;

import com.thepolo49.backend.model.invoice.MicroCompanyInvoice;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "expense-invoices")
@Data
@NoArgsConstructor
public class ExpenseInvoice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private ObjectId id;

    private String externalRef;

    private MicroCompanyInvoice microCompany;

    private Supplier supplier;

    private List<ExpenseInvoiceLine> invoiceLines;

    @Transient
    private Double amountExcludingTax;

    @Transient
    private Double amountWithTax;

    private LocalDate dueDate;

    private LocalDate paymentDate;

    // devis
    private Boolean quote;

    private String paymentMethod;

    private Boolean payed;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;


}
