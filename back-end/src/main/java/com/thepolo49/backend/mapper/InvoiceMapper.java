package com.thepolo49.backend.mapper;

import com.thepolo49.backend.dto.InvoiceDto;
import com.thepolo49.backend.model.Invoice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { ObjectIdMapper.class, InvoiceLineMapper.class })
public abstract class InvoiceMapper {

    public abstract Invoice convert(InvoiceDto invoiceDto);

    public abstract InvoiceDto convert(Invoice invoice);
    
}