package com.thepolo49.backend.mapper;

import com.thepolo49.backend.dto.UserDto;
import com.thepolo49.backend.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { ObjectIdMapper.class, RoleMapper.class } )
public abstract class UserMapper {
    public abstract User convert(UserDto userDto);

    @Mapping(target = "token", ignore = true)
    public abstract UserDto convert(User user);
    
}