package com.thepolo49.backend.model.invoice;

import com.thepolo49.backend.model.Address;
import com.thepolo49.backend.model.Manager;
import com.thepolo49.backend.model.user.User;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class MicroCompanyInvoice implements Serializable {

	private ObjectId id;

	private String siret;

	private String siren;

	private String companyName;

	private String email;

	private String phone;

	private Address address;

}
