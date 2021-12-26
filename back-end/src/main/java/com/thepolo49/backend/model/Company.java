package com.thepolo49.backend.model;

import com.thepolo49.backend.model.user.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "companies")
@Data
@NoArgsConstructor
public class Company implements Serializable {

	@Id
	private ObjectId id;

	@CreatedDate
	private LocalDateTime createdAt;
	@LastModifiedDate
	private LocalDateTime modifiedAt;

	@Indexed(unique=true)
	private String siret;

	@Indexed(unique=true)
	private String siren;

	@Indexed(unique=true)
	private String companyName;

	@DBRef
	private Manager manager;

	@DBRef
	private ContactInfo contactInfo;

	@DBRef
	private Address address;

}
