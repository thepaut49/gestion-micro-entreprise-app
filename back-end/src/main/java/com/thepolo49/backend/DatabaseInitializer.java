package com.thepolo49.backend;


import com.thepolo49.backend.model.user.ERole;
import com.thepolo49.backend.service.user.UserService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseInitializer implements ApplicationListener<ApplicationReadyEvent> {

    private final List<String> usernames = List.of("thepaut_antoine@outlook.fr",
            "friders49@hotmail.fr"
    );

    private final List<String> roles = List.of(
            ERole.USER_ADMIN.name(),
            ERole.SIMPLE_USER.name()
    );
    private final String password = "Pastel29000?";

    private final UserService userService;

    public DatabaseInitializer(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        /*for (int i = 0; i < usernames.size(); ++i) {
            CreateUserRequest request = new CreateUserRequest();
            request.setUsername(usernames.get(i));
            request.setPassword(password);
            request.setRePassword(password);
            request.setAuthorities(Set.of(roles.get(i)));

            userService.upsert(request);
        }*/
    }

}