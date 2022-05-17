package com.thepolo49.backend.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "accounting_codes")
@Data
@NoArgsConstructor
public class AccountingCode {

    @Id
    private Integer code;

    private String account;

    private String compteDuBilan;

    private String accountType;
}
