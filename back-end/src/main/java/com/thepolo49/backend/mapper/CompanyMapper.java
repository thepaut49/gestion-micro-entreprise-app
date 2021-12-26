package com.thepolo49.backend.mapper;

import com.thepolo49.backend.dto.company.CompanyDto;
import com.thepolo49.backend.model.Company;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { ObjectIdMapper.class, AddressMapper.class, ManagerMapper.class, ContactInfoMapper.class} )
public abstract class CompanyMapper {

    public abstract Company convert(CompanyDto CompanyDto);

    public abstract CompanyDto convert(Company Company);
    
}