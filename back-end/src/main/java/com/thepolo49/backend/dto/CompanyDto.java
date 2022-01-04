package com.thepolo49.backend.dto;


import com.thepolo49.backend.dto.commons.AddressDto;

import java.time.LocalDateTime;

public record CompanyDto(
        String id,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String companyName,
        String siren,
        String siret,
        ManagerDto manager,
        AddressDto address,
        String email,
        String phone
) {

}
