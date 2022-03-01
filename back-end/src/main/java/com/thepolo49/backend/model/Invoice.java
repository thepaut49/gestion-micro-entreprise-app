package com.thepolo49.backend.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "invoices")
@Data
@NoArgsConstructor
public class Invoice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private ObjectId id;

    // Expense or Revenue or Tax
    private InvoiceType invoiceType;

    private Company company;

    private Person person;

    @DBRef
    private MicroCompany microCompany;

    private List<InvoiceLine> invoiceLines;

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
