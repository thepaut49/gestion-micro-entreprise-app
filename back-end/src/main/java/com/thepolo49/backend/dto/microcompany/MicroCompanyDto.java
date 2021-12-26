package com.thepolo49.backend.dto.microcompany;

import com.googlecode.jmapper.annotations.JMap;
import com.thepolo49.backend.dto.commons.AddressDto;
import com.thepolo49.backend.dto.commons.ContactInfoDto;
import com.thepolo49.backend.dto.manager.ManagerDto;


import java.time.LocalDateTime;


public record MicroCompanyDto (
        String id,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String companyName,
        String siren,
        String siret,
        ManagerDto manager,
        AddressDto address,
        ContactInfoDto contactInfo
) {

}
