package com.thepolo49.backend.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Document(collection = "addresses")
@Data
@NoArgsConstructor
public class Address implements Serializable {

	@Id
	private ObjectId id;

	@CreatedDate
	private LocalDateTime createdAt;
	@LastModifiedDate
	private LocalDateTime modifiedAt;

	@Indexed(unique=true)
	private String addressLine1;

	@Indexed(unique=true)
	private String addressLine2;

	@Indexed(unique=true)
	private String cityName;

	private String countryName;

	@Indexed(unique=true)
	private String postalCode;

}
