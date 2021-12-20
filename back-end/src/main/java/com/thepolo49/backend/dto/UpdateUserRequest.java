package com.thepolo49.backend.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
public class UpdateUserRequest {

    private Set<String> authorities;

}