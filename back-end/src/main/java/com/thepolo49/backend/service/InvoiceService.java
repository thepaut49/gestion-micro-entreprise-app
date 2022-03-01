package com.thepolo49.backend.service;

import com.thepolo49.backend.dto.InvoiceDto;
import com.thepolo49.backend.exception.NotFoundException;
import com.thepolo49.backend.mapper.InvoiceMapper;
import com.thepolo49.backend.model.Invoice;
import com.thepolo49.backend.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;

    @Transactional
    public InvoiceDto create(InvoiceDto addressDto) {
        Invoice invoice = invoiceMapper.convert(addressDto);
        invoice = invoiceRepository.insert(invoice);
        return invoiceMapper.convert(invoice);
    }

    @Transactional
    public InvoiceDto update(InvoiceDto addressDto) {
        Invoice invoice = invoiceMapper.convert(addressDto);
        invoice = invoiceRepository.save(invoice);
        return invoiceMapper.convert(invoice);
    }

    @Transactional
    public void delete(ObjectId id) {
        Optional<Invoice> invoice = invoiceRepository.findById(id);
        if (invoice.isPresent()) {
            invoiceRepository.deleteById(id);
        }
        else {
            throw new NotFoundException(Invoice.class,id);
        }
    }

    public InvoiceDto getInvoice(ObjectId id) {
        Optional<Invoice> invoice =  invoiceRepository.findById(id);
        if (invoice.isPresent()) {
            return invoiceMapper.convert(invoice.get());
        }
        else {
            throw new NotFoundException(Invoice.class,id);
        }
    }

    public List<InvoiceDto> findAll() {
        return invoiceRepository.findAll()
                .stream()
                .map(address -> invoiceMapper.convert(address))
                .collect(Collectors.toList());
    }

}
