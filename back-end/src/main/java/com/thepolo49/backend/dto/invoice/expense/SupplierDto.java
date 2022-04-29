package com.thepolo49.backend.dto.invoice.expense;

import com.thepolo49.backend.dto.commons.AddressDto;

public record SupplierDto (
	String id,
	String name,
	String supplierType,
	String siret,
	String siren,
	AddressDto address,
	String phone,
	String email) {
}
