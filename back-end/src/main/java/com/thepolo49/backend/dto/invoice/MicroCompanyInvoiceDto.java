package com.thepolo49.backend.dto.invoice;

import com.thepolo49.backend.dto.commons.AddressDto;

import java.time.LocalDateTime;


public record MicroCompanyInvoiceDto(
        String id,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String companyName,
        String siren,
        String siret,
        AddressDto address,
        String email,
        String phone
) {

}
