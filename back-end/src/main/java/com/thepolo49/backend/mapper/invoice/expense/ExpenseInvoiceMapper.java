package com.thepolo49.backend.mapper.invoice.expense;

import com.thepolo49.backend.dto.invoice.expense.ExpenseInvoiceDto;
import com.thepolo49.backend.mapper.ObjectIdMapper;
import com.thepolo49.backend.mapper.invoice.MicroCompanyInvoiceMapper;
import com.thepolo49.backend.model.invoice.expense.ExpenseInvoice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { ObjectIdMapper.class, ExpenseInvoiceLineMapper.class, SupplierMapper.class, MicroCompanyInvoiceMapper.class})
public abstract class ExpenseInvoiceMapper {

    public abstract ExpenseInvoice convert(ExpenseInvoiceDto invoiceDto);

    public abstract ExpenseInvoiceDto convert(ExpenseInvoice invoice);
    
}