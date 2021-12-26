package com.thepolo49.backend.repository.user;

import com.thepolo49.backend.dto.Page;
import com.thepolo49.backend.dto.SearchUsersQuery;
import com.thepolo49.backend.model.user.User;

import java.util.List;

public interface UserRepoCustom {
    List<User> searchUsers(Page page, SearchUsersQuery query);
}
