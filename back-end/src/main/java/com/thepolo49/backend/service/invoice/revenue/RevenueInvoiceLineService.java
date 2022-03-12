package com.thepolo49.backend.service.invoice.revenue;

import com.thepolo49.backend.dto.invoice.revenue.RevenueInvoiceLineDto;
import com.thepolo49.backend.exception.NotFoundException;
import com.thepolo49.backend.mapper.invoice.revenue.RevenueInvoiceLineMapper;
import com.thepolo49.backend.model.invoice.revenue.RevenueInvoiceLine;
import com.thepolo49.backend.repository.invoice.revenue.RevenueInvoiceLineRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RevenueInvoiceLineService {

    private final RevenueInvoiceLineRepository revenueInvoiceLineRepository;
    private final RevenueInvoiceLineMapper revenueInvoiceLineMapper;

    @Transactional
    public RevenueInvoiceLineDto create(RevenueInvoiceLineDto invoiceLineDto) {
        RevenueInvoiceLine revenueInvoiceLine = revenueInvoiceLineMapper.convert(invoiceLineDto);
        revenueInvoiceLine = revenueInvoiceLineRepository.insert(revenueInvoiceLine);
        return revenueInvoiceLineMapper.convert(revenueInvoiceLine);
    }

    @Transactional
    public RevenueInvoiceLineDto update(RevenueInvoiceLineDto revenueInvoiceLineDto) {
        RevenueInvoiceLine revenueInvoiceLine = revenueInvoiceLineMapper.convert(revenueInvoiceLineDto);
        revenueInvoiceLine = revenueInvoiceLineRepository.save(revenueInvoiceLine);
        return revenueInvoiceLineMapper.convert(revenueInvoiceLine);
    }

    @Transactional
    public void delete(ObjectId id) {
        Optional<RevenueInvoiceLine> revenueInvoiceLineOptional = revenueInvoiceLineRepository.findById(id);
        if (revenueInvoiceLineOptional.isPresent()) {
            revenueInvoiceLineRepository.deleteById(id);
        }
        else {
            throw new NotFoundException(RevenueInvoiceLine.class,id);
        }
    }

    public RevenueInvoiceLineDto getRevenueInvoiceLine(ObjectId id) {
        Optional<RevenueInvoiceLine> revenueInvoiceLineOptional =  revenueInvoiceLineRepository.findById(id);
        if (revenueInvoiceLineOptional.isPresent()) {
            return revenueInvoiceLineMapper.convert(revenueInvoiceLineOptional.get());
        }
        else {
            throw new NotFoundException(RevenueInvoiceLine.class,id);
        }
    }

    public List<RevenueInvoiceLineDto> findAll() {
        return revenueInvoiceLineRepository.findAll()
                .stream()
                .map(address -> revenueInvoiceLineMapper.convert(address))
                .collect(Collectors.toList());
    }

}
