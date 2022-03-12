package com.thepolo49.backend.dto.invoice.expense;

import com.thepolo49.backend.dto.commons.AddressDto;

public record SupplierDto (
	String companyId,
	String personId,
	String supplierType,
	AddressDto address,
	String phone,
	String email) {
}
