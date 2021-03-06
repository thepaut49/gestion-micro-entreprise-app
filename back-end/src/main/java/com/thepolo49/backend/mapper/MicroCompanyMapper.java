package com.thepolo49.backend.mapper;

import com.thepolo49.backend.dto.MicroCompanyDto;
import com.thepolo49.backend.model.MicroCompany;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { RoleMapper.class, ObjectIdMapper.class, UserMapper.class})
public abstract class MicroCompanyMapper {

    public abstract MicroCompany convert(MicroCompanyDto microCompanyDto);

    public abstract MicroCompanyDto convert(MicroCompany microCompany);
    
}