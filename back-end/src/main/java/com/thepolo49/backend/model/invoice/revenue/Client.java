package com.thepolo49.backend.model.invoice.revenue;

import com.thepolo49.backend.model.Address;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class Client implements Serializable {

	private ObjectId id;

	private String name;

	private ClientType clientType;

	private String siret;

	private String siren;

	private Address address;

	private String phone;

	private String email;

}
