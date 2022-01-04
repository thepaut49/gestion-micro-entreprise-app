package com.thepolo49.backend.mapper;

import com.thepolo49.backend.dto.MicroCompanyDto;
import com.thepolo49.backend.model.MicroCompany;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { RoleMapper.class, ObjectIdMapper.class, AddressMapper.class, ManagerMapper.class})
public abstract class MicroCompanyMapper {

    public abstract MicroCompany convert(MicroCompanyDto MicroCompanyDto);

    public abstract MicroCompanyDto convert(MicroCompany MicroCompany);
    
}