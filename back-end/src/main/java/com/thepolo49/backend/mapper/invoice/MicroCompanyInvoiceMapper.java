package com.thepolo49.backend.mapper.invoice;

import com.thepolo49.backend.dto.invoice.MicroCompanyInvoiceDto;
import com.thepolo49.backend.mapper.ObjectIdMapper;
import com.thepolo49.backend.model.invoice.MicroCompanyInvoice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { ObjectIdMapper.class})
public abstract class MicroCompanyInvoiceMapper {

    public abstract MicroCompanyInvoice convert(MicroCompanyInvoiceDto microCompanyInvoiceDto);

    public abstract MicroCompanyInvoiceDto convert(MicroCompanyInvoice microCompanyInvoice);
    
}