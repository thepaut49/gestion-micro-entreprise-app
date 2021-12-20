package com.thepolo49.backend.mapper;

import com.thepolo49.backend.dto.UserView;
import com.thepolo49.backend.model.user.User;
import com.thepolo49.backend.repository.user.UserRepo;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ObjectIdMapper.class})
public abstract class UserViewMapper {

    @Autowired
    private UserRepo userRepo;

    public abstract UserView toUserView(User user);

    public abstract List<UserView> toUserView(List<User> users);

    public UserView toUserViewById(ObjectId id) {
        if (id == null) {
            return null;
        }
        return toUserView(userRepo.getById(id));
    }

}