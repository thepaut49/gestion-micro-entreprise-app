package com.thepolo49.backend.mapper;

import com.thepolo49.backend.dto.commons.AddressDto;
import com.thepolo49.backend.model.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ObjectIdMapper.class)
public abstract class AddressMapper {

    public abstract Address convert(AddressDto AddressDto);

    public abstract AddressDto convert(Address Address);
    
}