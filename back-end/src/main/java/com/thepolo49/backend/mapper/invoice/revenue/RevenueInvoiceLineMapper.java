package com.thepolo49.backend.mapper.invoice.revenue;

import com.thepolo49.backend.dto.invoice.revenue.RevenueInvoiceLineDto;
import com.thepolo49.backend.mapper.AccountingCodeMapper;
import com.thepolo49.backend.mapper.ObjectIdMapper;
import com.thepolo49.backend.model.invoice.revenue.RevenueInvoiceLine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper( componentModel = "spring", uses = { ObjectIdMapper.class, AccountingCodeMapper.class, })
public abstract class RevenueInvoiceLineMapper {

    @Mapping(target="invoiceLineId.invoiceId", source="invoiceLineDto.invoiceId")
    @Mapping(target="invoiceLineId.lineNumber", source="invoiceLineDto.lineNumber")
    public abstract RevenueInvoiceLine convert(RevenueInvoiceLineDto invoiceLineDto);

    @Mapping(target="invoiceId", source="invoiceLine.invoiceLineId.invoiceId")
    @Mapping(target="lineNumber", source="invoiceLine.invoiceLineId.lineNumber")
    public abstract RevenueInvoiceLineDto convert(RevenueInvoiceLine invoiceLine);
    
}