package com.thepolo49.backend.mapper.invoice.expense;

import com.thepolo49.backend.dto.invoice.expense.ExpenseInvoiceLineDto;
import com.thepolo49.backend.mapper.AccountingCodeMapper;
import com.thepolo49.backend.mapper.ObjectIdMapper;
import com.thepolo49.backend.model.invoice.expense.ExpenseInvoiceLine;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring", uses = { ObjectIdMapper.class, AccountingCodeMapper.class, })
public abstract class ExpenseInvoiceLineMapper {

    public abstract ExpenseInvoiceLine convert(ExpenseInvoiceLineDto invoiceLineDto);

    public abstract ExpenseInvoiceLineDto convert(ExpenseInvoiceLine invoiceLine);

    @BeforeMapping
    protected void amountsCalculation(ExpenseInvoiceLine invoiceLine) {
        invoiceLine.setAmountExcludingTax( invoiceLine.getQuantity() * invoiceLine.getAmountForRefQuantity());
        invoiceLine.setAmountWithTax(invoiceLine.getAmountExcludingTax() +  (invoiceLine.getAmountExcludingTax() * invoiceLine.getTaxPercentage()) / 100.0);
        invoiceLine.setAmountExcludingTax(Math.round(invoiceLine.getAmountExcludingTax() * 100.0) / 100.0);
        invoiceLine.setAmountWithTax(Math.round(invoiceLine.getAmountWithTax() * 100.0) / 100.0);
    }
    
}