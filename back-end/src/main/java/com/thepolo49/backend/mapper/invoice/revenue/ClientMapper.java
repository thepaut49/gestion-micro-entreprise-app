package com.thepolo49.backend.mapper.invoice.revenue;

import com.thepolo49.backend.dto.invoice.revenue.ClientDto;
import com.thepolo49.backend.dto.invoice.revenue.RevenueInvoiceDto;
import com.thepolo49.backend.mapper.ObjectIdMapper;
import com.thepolo49.backend.model.invoice.revenue.Client;
import com.thepolo49.backend.model.invoice.revenue.RevenueInvoice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { ObjectIdMapper.class })
public abstract class ClientMapper {

    public abstract Client convert(ClientDto clientDto);

    public abstract ClientDto convert(Client client);
    
}