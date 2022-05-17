package com.thepolo49.backend.mapper.invoice.expense;

import com.thepolo49.backend.dto.invoice.expense.ExpenseInvoiceDto;
import com.thepolo49.backend.mapper.ObjectIdMapper;
import com.thepolo49.backend.mapper.invoice.MicroCompanyInvoiceMapper;
import com.thepolo49.backend.model.invoice.expense.ExpenseInvoice;
import com.thepolo49.backend.model.invoice.expense.ExpenseInvoiceLine;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { ObjectIdMapper.class, ExpenseInvoiceLineMapper.class, SupplierMapper.class, MicroCompanyInvoiceMapper.class, ExpenseInvoiceLineMapper.class})
public abstract class ExpenseInvoiceMapper {

    public abstract ExpenseInvoice convert(ExpenseInvoiceDto invoiceDto);

    public abstract ExpenseInvoiceDto convert(ExpenseInvoice invoice);

    @BeforeMapping
    protected void amountsCalculation(ExpenseInvoice invoice) {


        double amountExcludingtax = 0.0d;
        double amountWithtax = 0.0d;

        for(ExpenseInvoiceLine line : invoice.getInvoiceLines()) {
            amountsCalculationForLines(line);
            amountExcludingtax += line.getAmountExcludingTax() != null  ? line.getAmountExcludingTax() : 0.0d;
            amountWithtax += line.getAmountWithTax() != null  ? line.getAmountWithTax() : 0.0d;
        }
        invoice.setAmountExcludingTax(Math.round(amountExcludingtax * 100.0) / 100.0);
        invoice.setAmountWithTax(Math.round(amountWithtax * 100.0) / 100.0);
    }

    protected void amountsCalculationForLines(ExpenseInvoiceLine invoiceLine) {
        invoiceLine.setAmountExcludingTax( invoiceLine.getQuantity() * invoiceLine.getAmountForRefQuantity());
        invoiceLine.setAmountWithTax(invoiceLine.getAmountExcludingTax() +  (invoiceLine.getAmountExcludingTax() * invoiceLine.getTaxPercentage()) / 100.0);
        invoiceLine.setAmountExcludingTax(Math.round(invoiceLine.getAmountExcludingTax() * 100.0) / 100.0);
        invoiceLine.setAmountWithTax(Math.round(invoiceLine.getAmountWithTax() * 100.0) / 100.0);
    }

    
}