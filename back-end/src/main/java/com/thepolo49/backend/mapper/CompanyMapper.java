package com.thepolo49.backend.mapper;

import com.thepolo49.backend.dto.CompanyDto;
import com.thepolo49.backend.model.Company;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { ObjectIdMapper.class} )
public abstract class CompanyMapper {

    public abstract Company convert(CompanyDto companyDto);

    public abstract CompanyDto convert(Company company);
    
}