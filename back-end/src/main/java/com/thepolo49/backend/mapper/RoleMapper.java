package com.thepolo49.backend.mapper;

import com.thepolo49.backend.model.user.Role;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class RoleMapper {

    public String roleToString(Role role) {
        return role.getAuthority() ;
    }

    public Role stringToRole(String string) {
        if (string == null) {
            return null;
        }
        return new Role(string);
    }

}