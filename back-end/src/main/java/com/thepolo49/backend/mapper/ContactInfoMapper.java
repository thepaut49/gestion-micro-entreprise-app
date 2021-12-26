package com.thepolo49.backend.mapper;


import com.thepolo49.backend.dto.commons.ContactInfoDto;
import com.thepolo49.backend.model.ContactInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ObjectIdMapper.class)
public abstract class ContactInfoMapper {

    public abstract ContactInfo convert(ContactInfoDto ContactInfoDto);

    public abstract ContactInfoDto convert(ContactInfo ContactInfo);
    
}