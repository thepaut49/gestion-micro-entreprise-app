package com.thepolo49.backend.dto.invoice.revenue;

import com.thepolo49.backend.dto.commons.AddressDto;

public record ClientDto(
	String companyId,
	String personId,
	String clientType,
	AddressDto address,
	String phone,
	String email) {
}
