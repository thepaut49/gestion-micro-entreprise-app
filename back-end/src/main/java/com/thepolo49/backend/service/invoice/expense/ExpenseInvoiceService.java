package com.thepolo49.backend.service.invoice.expense;


import com.thepolo49.backend.dto.invoice.expense.ExpenseInvoiceDto;
import com.thepolo49.backend.exception.NotFoundException;

import com.thepolo49.backend.mapper.invoice.expense.ExpenseInvoiceMapper;
import com.thepolo49.backend.model.invoice.expense.ExpenseInvoice;
import com.thepolo49.backend.repository.invoice.expense.ExpenseInvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseInvoiceService {

    private final ExpenseInvoiceRepository expenseInvoiceRepository;
    private final ExpenseInvoiceMapper expenseInvoiceMapper;

    @Transactional
    public ExpenseInvoiceDto create(ExpenseInvoiceDto expenseInvoiceDto) {
        ExpenseInvoice expenseInvoice = expenseInvoiceMapper.convert(expenseInvoiceDto);
        expenseInvoice = expenseInvoiceRepository.insert(expenseInvoice);
        return expenseInvoiceMapper.convert(expenseInvoice);
    }

    @Transactional
    public ExpenseInvoiceDto update(ExpenseInvoiceDto expenseInvoiceDto) {
        ExpenseInvoice expenseInvoice = expenseInvoiceMapper.convert(expenseInvoiceDto);
        expenseInvoice = expenseInvoiceRepository.save(expenseInvoice);
        return expenseInvoiceMapper.convert(expenseInvoice);
    }

    @Transactional
    public void delete(ObjectId id) {
        Optional<ExpenseInvoice> expenseInvoiceOptional = expenseInvoiceRepository.findById(id);
        if (expenseInvoiceOptional.isPresent()) {
            expenseInvoiceRepository.deleteById(id);
        }
        else {
            throw new NotFoundException(ExpenseInvoice.class,id);
        }
    }

    public ExpenseInvoiceDto getExpenseInvoice(ObjectId id) {
        Optional<ExpenseInvoice> expenseInvoiceOptional =  expenseInvoiceRepository.findById(id);
        if (expenseInvoiceOptional.isPresent()) {
            return expenseInvoiceMapper.convert(expenseInvoiceOptional.get());
        }
        else {
            throw new NotFoundException(ExpenseInvoice.class,id);
        }
    }

    public List<ExpenseInvoiceDto> findAll() {
        return expenseInvoiceRepository.findAll()
                .stream()
                .map(expenseInvoiceMapper::convert)
                .toList();
    }

}
