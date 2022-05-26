package com.thepolo49.backend.mapper.invoice.revenue;

import com.thepolo49.backend.dto.invoice.revenue.RevenueInvoiceLineDto;
import com.thepolo49.backend.mapper.AccountingCodeMapper;
import com.thepolo49.backend.mapper.ObjectIdMapper;
import com.thepolo49.backend.model.invoice.revenue.RevenueInvoiceLine;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring", uses = { ObjectIdMapper.class, AccountingCodeMapper.class, })
public abstract class RevenueInvoiceLineMapper {

    public abstract RevenueInvoiceLine convert(RevenueInvoiceLineDto invoiceLineDto);

    public abstract RevenueInvoiceLineDto convert(RevenueInvoiceLine invoiceLine);

    @BeforeMapping
    protected void amountsCalculation(RevenueInvoiceLine invoiceLine) {
        invoiceLine.setAmountExcludingTax( invoiceLine.getQuantity() * invoiceLine.getAmountForRefQuantity());
        invoiceLine.setAmountWithTax(invoiceLine.getAmountExcludingTax() +  (invoiceLine.getAmountExcludingTax() * invoiceLine.getTaxPercentage()) / 100.0);
        invoiceLine.setAmountExcludingTax(Math.round(invoiceLine.getAmountExcludingTax() * 100.0) / 100.0);
        invoiceLine.setAmountWithTax(Math.round(invoiceLine.getAmountWithTax() * 100.0) / 100.0);
    }
    
}