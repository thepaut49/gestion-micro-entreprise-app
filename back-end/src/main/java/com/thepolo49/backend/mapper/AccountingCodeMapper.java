package com.thepolo49.backend.mapper;

import com.thepolo49.backend.dto.AccountingCodeDto;
import com.thepolo49.backend.model.AccountingCode;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ObjectIdMapper.class)
public abstract class AccountingCodeMapper {

    public abstract AccountingCode convert(AccountingCodeDto AccountingCodeDto);

    public abstract AccountingCodeDto convert(AccountingCode AccountingCode);
    
}