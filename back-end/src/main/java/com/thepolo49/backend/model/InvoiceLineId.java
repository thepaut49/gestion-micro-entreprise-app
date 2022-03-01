package com.thepolo49.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceLineId implements Serializable {

    private static final long serialVersionUID = 1L;

    private ObjectId invoiceId;

    private Integer lineNumber;

}