package com.thepolo49.backend.mapper;

import com.thepolo49.backend.dto.InvoiceLineDto;
import com.thepolo49.backend.model.InvoiceLine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper( componentModel = "spring", uses = { ObjectIdMapper.class, AccountingCodeMapper.class})
public abstract class InvoiceLineMapper {

    @Mapping(target="invoiceLineId.invoiceId", source="invoiceLineDto.invoiceId")
    @Mapping(target="invoiceLineId.lineNumber", source="invoiceLineDto.lineNumber")
    public abstract InvoiceLine convert(InvoiceLineDto invoiceLineDto);

    @Mapping(target="invoiceId", source="invoiceLine.invoiceLineId.invoiceId")
    @Mapping(target="lineNumber", source="invoiceLine.invoiceLineId.lineNumber")
    public abstract InvoiceLineDto convert(InvoiceLine invoiceLine);
    
}