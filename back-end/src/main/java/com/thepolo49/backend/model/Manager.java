package com.thepolo49.backend.model;

import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@ToString
public class Manager implements Serializable {

    private String familyName;

    private String firstName;

    private String email;

    private String phone;

}
