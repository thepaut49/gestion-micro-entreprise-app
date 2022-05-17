package com.thepolo49.backend.model.invoice;

import com.thepolo49.backend.model.Address;
import lombok.*;
import org.bson.types.ObjectId;


import java.io.Serializable;

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
