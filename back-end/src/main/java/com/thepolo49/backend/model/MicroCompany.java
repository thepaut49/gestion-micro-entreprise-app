package com.thepolo49.backend.model;

import com.thepolo49.backend.model.user.User;
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

@Document(collection = "micro_companies")
@Data
@NoArgsConstructor
public class MicroCompany implements Serializable {

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

	private String email;

	private String phone;

	private Manager manager;

	private Address address;

	@DBRef
	private User user;

	private User accountant;

	private boolean accessibleByAdmin;

}
