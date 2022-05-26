package com.thepolo49.backend.mapper.invoice.revenue;

import com.thepolo49.backend.dto.invoice.revenue.RevenueInvoiceDto;
import com.thepolo49.backend.mapper.ObjectIdMapper;
import com.thepolo49.backend.mapper.invoice.MicroCompanyInvoiceMapper;
import com.thepolo49.backend.model.invoice.expense.ExpenseInvoice;
import com.thepolo49.backend.model.invoice.expense.ExpenseInvoiceLine;
import com.thepolo49.backend.model.invoice.revenue.RevenueInvoice;
import com.thepolo49.backend.model.invoice.revenue.RevenueInvoiceLine;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { ObjectIdMapper.class, RevenueInvoiceLineMapper.class, ClientMapper.class, MicroCompanyInvoiceMapper.class })
public abstract class RevenueInvoiceMapper {

    public abstract RevenueInvoice convert(RevenueInvoiceDto invoiceDto);

    public abstract RevenueInvoiceDto convert(RevenueInvoice invoice);

    @BeforeMapping
    protected void amountsCalculation(RevenueInvoice invoice) {

        double amountExcludingtax = 0.0d;
        double amountWithtax = 0.0d;

        for(RevenueInvoiceLine line : invoice.getInvoiceLines()) {
            amountsCalculationForLines(line);
            amountExcludingtax += line.getAmountExcludingTax() != null  ? line.getAmountExcludingTax() : 0.0d;
            amountWithtax += line.getAmountWithTax() != null  ? line.getAmountWithTax() : 0.0d;
        }
        invoice.setAmountExcludingTax(Math.round(amountExcludingtax * 100.0) / 100.0);
        invoice.setAmountWithTax(Math.round(amountWithtax * 100.0) / 100.0);
    }

    protected void amountsCalculationForLines(RevenueInvoiceLine invoiceLine) {
        invoiceLine.setAmountExcludingTax( invoiceLine.getQuantity() * invoiceLine.getAmountForRefQuantity());
        invoiceLine.setAmountWithTax(invoiceLine.getAmountExcludingTax() +  (invoiceLine.getAmountExcludingTax() * invoiceLine.getTaxPercentage()) / 100.0);
        invoiceLine.setAmountExcludingTax(Math.round(invoiceLine.getAmountExcludingTax() * 100.0) / 100.0);
        invoiceLine.setAmountWithTax(Math.round(invoiceLine.getAmountWithTax() * 100.0) / 100.0);
    }
    
}