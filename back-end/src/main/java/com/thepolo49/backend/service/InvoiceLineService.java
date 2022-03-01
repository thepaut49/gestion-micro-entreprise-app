package com.thepolo49.backend.service;

import com.thepolo49.backend.dto.InvoiceLineDto;
import com.thepolo49.backend.exception.NotFoundException;
import com.thepolo49.backend.mapper.InvoiceLineMapper;
import com.thepolo49.backend.model.InvoiceLine;
import com.thepolo49.backend.repository.InvoiceLineRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoiceLineService {

    private final InvoiceLineRepository invoiceLineRepository;
    private final InvoiceLineMapper invoiceLineMapper;

    @Transactional
    public InvoiceLineDto create(InvoiceLineDto addressDto) {
        InvoiceLine invoice = invoiceLineMapper.convert(addressDto);
        invoice = invoiceLineRepository.insert(invoice);
        return invoiceLineMapper.convert(invoice);
    }

    @Transactional
    public InvoiceLineDto update(InvoiceLineDto addressDto) {
        InvoiceLine invoice = invoiceLineMapper.convert(addressDto);
        invoice = invoiceLineRepository.save(invoice);
        return invoiceLineMapper.convert(invoice);
    }

    @Transactional
    public void delete(ObjectId id) {
        Optional<InvoiceLine> invoice = invoiceLineRepository.findById(id);
        if (invoice.isPresent()) {
            invoiceLineRepository.deleteById(id);
        }
        else {
            throw new NotFoundException(InvoiceLine.class,id);
        }
    }

    public InvoiceLineDto getInvoiceLine(ObjectId id) {
        Optional<InvoiceLine> invoice =  invoiceLineRepository.findById(id);
        if (invoice.isPresent()) {
            return invoiceLineMapper.convert(invoice.get());
        }
        else {
            throw new NotFoundException(InvoiceLine.class,id);
        }
    }

    public List<InvoiceLineDto> findAll() {
        return invoiceLineRepository.findAll()
                .stream()
                .map(address -> invoiceLineMapper.convert(address))
                .collect(Collectors.toList());
    }

}
