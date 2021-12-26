package com.thepolo49.backend.mapper;

import com.thepolo49.backend.dto.person.PersonDto;
import com.thepolo49.backend.model.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { ObjectIdMapper.class, AddressMapper.class, ContactInfoMapper.class})
public abstract class PersonMapper {

    public abstract Person convert(PersonDto PersonDto);

    public abstract PersonDto convert(Person Person);
    
}