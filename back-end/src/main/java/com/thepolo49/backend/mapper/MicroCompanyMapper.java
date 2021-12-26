package com.thepolo49.backend.mapper;

import com.thepolo49.backend.dto.microcompany.MicroCompanyDto;
import com.thepolo49.backend.model.MicroCompany;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { ObjectIdMapper.class, AddressMapper.class, ManagerMapper.class, ContactInfoMapper.class})
public abstract class MicroCompanyMapper {

    public abstract MicroCompany convert(MicroCompanyDto MicroCompanyDto);

    public abstract MicroCompanyDto convert(MicroCompany MicroCompany);
    
}