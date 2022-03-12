package com.thepolo49.backend.model.invoice.expense;

import com.thepolo49.backend.model.Address;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class Supplier implements Serializable {

	private ObjectId companyId;

	private ObjectId personId;

	private SupplierType supplierType;

	private Address address;

	private String phone;

	private String email;

}
