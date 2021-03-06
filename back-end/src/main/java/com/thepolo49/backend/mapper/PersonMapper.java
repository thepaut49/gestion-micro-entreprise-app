package com.thepolo49.backend.mapper;

import com.thepolo49.backend.dto.PersonDto;
import com.thepolo49.backend.model.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { ObjectIdMapper.class})
public abstract class PersonMapper {

    public abstract Person convert(PersonDto personDto);

    public abstract PersonDto convert(Person person);
    
}