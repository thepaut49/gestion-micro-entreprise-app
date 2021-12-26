package com.thepolo49.backend.mapper;

import com.thepolo49.backend.dto.CreateUserRequest;
import com.thepolo49.backend.dto.UpdateUserRequest;
import com.thepolo49.backend.dto.manager.ManagerDto;
import com.thepolo49.backend.model.Manager;
import com.thepolo49.backend.model.user.Role;
import com.thepolo49.backend.model.user.User;
import org.mapstruct.*;

import static java.util.stream.Collectors.toSet;
import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring", uses = { ObjectIdMapper.class, ContactInfoMapper.class})
public abstract class ManagerMapper {

    public abstract Manager convert(ManagerDto managerDto);

    public abstract ManagerDto convert(Manager manager);

}