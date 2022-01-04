package com.thepolo49.backend.mapper;

import com.thepolo49.backend.dto.ManagerDto;
import com.thepolo49.backend.model.Manager;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = { ObjectIdMapper.class})
public abstract class ManagerMapper {

    public abstract Manager convert(ManagerDto managerDto);

    public abstract ManagerDto convert(Manager manager);

}