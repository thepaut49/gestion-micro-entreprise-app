package com.thepolo49.backend.mapper.invoice.expense;

import com.thepolo49.backend.dto.invoice.expense.ExpenseInvoiceLineDto;
import com.thepolo49.backend.mapper.AccountingCodeMapper;
import com.thepolo49.backend.mapper.ObjectIdMapper;
import com.thepolo49.backend.model.invoice.expense.ExpenseInvoiceLine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper( componentModel = "spring", uses = { ObjectIdMapper.class, AccountingCodeMapper.class, })
public abstract class ExpenseInvoiceLineMapper {

    @Mapping(target="invoiceLineId.invoiceId", source="invoiceLineDto.invoiceId")
    @Mapping(target="invoiceLineId.lineNumber", source="invoiceLineDto.lineNumber")
    public abstract ExpenseInvoiceLine convert(ExpenseInvoiceLineDto invoiceLineDto);

    @Mapping(target="invoiceId", source="invoiceLine.invoiceLineId.invoiceId")
    @Mapping(target="lineNumber", source="invoiceLine.invoiceLineId.lineNumber")
    public abstract ExpenseInvoiceLineDto convert(ExpenseInvoiceLine invoiceLine);
    
}