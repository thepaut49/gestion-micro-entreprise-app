package com.thepolo49.backend.mapper.invoice.revenue;

import com.thepolo49.backend.dto.invoice.revenue.RevenueInvoiceDto;
import com.thepolo49.backend.mapper.ObjectIdMapper;
import com.thepolo49.backend.mapper.invoice.MicroCompanyInvoiceMapper;
import com.thepolo49.backend.model.invoice.revenue.RevenueInvoice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { ObjectIdMapper.class, RevenueInvoiceLineMapper.class, ClientMapper.class, MicroCompanyInvoiceMapper.class })
public abstract class RevenueInvoiceMapper {

    public abstract RevenueInvoice convert(RevenueInvoiceDto invoiceDto);

    public abstract RevenueInvoiceDto convert(RevenueInvoice invoice);
    
}