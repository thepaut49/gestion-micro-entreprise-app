package com.thepolo49.backend.mapper.invoice.expense;

import com.thepolo49.backend.dto.invoice.expense.SupplierDto;
import com.thepolo49.backend.mapper.ObjectIdMapper;
import com.thepolo49.backend.model.invoice.expense.Supplier;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { ObjectIdMapper.class })
public abstract class SupplierMapper {

    public abstract Supplier convert(SupplierDto supplierDto);

    public abstract SupplierDto convert(Supplier supplier);
    
}