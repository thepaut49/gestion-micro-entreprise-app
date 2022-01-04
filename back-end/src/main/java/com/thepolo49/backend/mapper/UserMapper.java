package com.thepolo49.backend.mapper;

import com.thepolo49.backend.dto.UserDto;
import com.thepolo49.backend.model.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { ObjectIdMapper.class, RoleMapper.class } )
public abstract class UserMapper {

    public abstract User convert(UserDto userDto);

    public abstract UserDto convert(User Company);
    
}