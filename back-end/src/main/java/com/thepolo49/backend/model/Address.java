package com.thepolo49.backend.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class Address implements Serializable {

	private String addressLine1;

	private String addressLine2;

	private String cityName;

	private String countryName;

	private String postalCode;

}
