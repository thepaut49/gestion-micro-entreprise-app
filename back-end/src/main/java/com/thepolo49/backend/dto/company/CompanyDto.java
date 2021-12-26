package com.thepolo49.backend.dto.company;


import com.googlecode.jmapper.annotations.JGlobalMap;
import com.thepolo49.backend.dto.commons.AddressDto;
import com.thepolo49.backend.dto.commons.ContactInfoDto;
import com.thepolo49.backend.dto.manager.ManagerDto;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@JGlobalMap
public record CompanyDto(
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
