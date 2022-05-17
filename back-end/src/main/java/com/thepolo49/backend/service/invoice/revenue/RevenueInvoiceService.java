package com.thepolo49.backend.service.invoice.revenue;

import com.thepolo49.backend.dto.invoice.revenue.RevenueInvoiceDto;
import com.thepolo49.backend.exception.NotFoundException;
import com.thepolo49.backend.mapper.invoice.revenue.RevenueInvoiceMapper;
import com.thepolo49.backend.model.invoice.revenue.RevenueInvoice;
import com.thepolo49.backend.repository.invoice.revenue.RevenueInvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RevenueInvoiceService {

    private final RevenueInvoiceRepository revenueInvoiceRepository;
    private final RevenueInvoiceMapper revenueInvoiceMapper;

    @Transactional
    public RevenueInvoiceDto create(RevenueInvoiceDto revenueInvoiceDto) {
        RevenueInvoice revenueInvoice = revenueInvoiceMapper.convert(revenueInvoiceDto);
        revenueInvoice = revenueInvoiceRepository.insert(revenueInvoice);
        return revenueInvoiceMapper.convert(revenueInvoice);
    }

    @Transactional
    public RevenueInvoiceDto update(RevenueInvoiceDto revenueInvoiceDto) {
        RevenueInvoice revenueInvoice = revenueInvoiceMapper.convert(revenueInvoiceDto);
        revenueInvoice = revenueInvoiceRepository.save(revenueInvoice);
        return revenueInvoiceMapper.convert(revenueInvoice);
    }

    @Transactional
    public void delete(ObjectId id) {
        Optional<RevenueInvoice> revenueInvoiceOptional = revenueInvoiceRepository.findById(id);
        if (revenueInvoiceOptional.isPresent()) {
            revenueInvoiceRepository.deleteById(id);
        }
        else {
            throw new NotFoundException(RevenueInvoice.class,id);
        }
    }

    public RevenueInvoiceDto getRevenueInvoice(ObjectId id) {
        Optional<RevenueInvoice> revenueInvoiceOptional =  revenueInvoiceRepository.findById(id);
        if (revenueInvoiceOptional.isPresent()) {
            return revenueInvoiceMapper.convert(revenueInvoiceOptional.get());
        }
        else {
            throw new NotFoundException(RevenueInvoice.class,id);
        }
    }

    public List<RevenueInvoiceDto> findAll() {
        return revenueInvoiceRepository.findAll()
                .stream()
                .map(revenueInvoiceMapper::convert).toList();
    }

}
