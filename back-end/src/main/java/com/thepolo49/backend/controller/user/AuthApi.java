package com.thepolo49.backend.controller.user;


import com.thepolo49.backend.configuration.security.JwtTokenUtil;
import com.thepolo49.backend.dto.*;
import com.thepolo49.backend.mapper.LoginMapper;
import com.thepolo49.backend.mapper.UserMapper;
import com.thepolo49.backend.model.user.User;
import com.thepolo49.backend.service.user.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "Authentication")
@RestController
@RequestMapping(path = "api/public")
@RequiredArgsConstructor
public class AuthApi {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final LoginMapper loginMapper;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid AuthRequest request) {
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            User user = (User) authenticate.getPrincipal();
            LoginResponseDto loginResponseDto = loginMapper.convert(user);
            loginResponseDto.setToken(jwtTokenUtil.generateAccessToken(user));

            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, loginResponseDto.getToken())
                    .body(loginResponseDto);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register")
    public UserView register(@RequestBody @Valid CreateUserRequest request) {
        return userService.create(request);
    }

}