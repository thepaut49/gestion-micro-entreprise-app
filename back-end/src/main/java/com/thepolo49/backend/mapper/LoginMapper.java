package com.thepolo49.backend.mapper;

import com.thepolo49.backend.dto.LoginResponseDto;
import com.thepolo49.backend.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { ObjectIdMapper.class, RoleMapper.class } )
public abstract class LoginMapper {
    public abstract User convert(LoginResponseDto loginResponseDto);

    @Mapping(target = "token", ignore = true)
    public abstract LoginResponseDto convert(User user);
    
}