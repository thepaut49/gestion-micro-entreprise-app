package com.thepolo49.backend.service.invoice.expense;


import com.thepolo49.backend.dto.invoice.expense.ExpenseInvoiceLineDto;
import com.thepolo49.backend.exception.NotFoundException;
import com.thepolo49.backend.mapper.invoice.expense.ExpenseInvoiceLineMapper;
import com.thepolo49.backend.model.invoice.expense.ExpenseInvoiceLine;
import com.thepolo49.backend.repository.invoice.expense.ExpenseInvoiceLineRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseInvoiceLineService {

    private final ExpenseInvoiceLineRepository expenseInvoiceLineRepository;
    private final ExpenseInvoiceLineMapper expenseInvoiceLineMapper;

    @Transactional
    public ExpenseInvoiceLineDto create(ExpenseInvoiceLineDto expenseInvoiceLineDto) {
        ExpenseInvoiceLine expenseInvoiceLine = expenseInvoiceLineMapper.convert(expenseInvoiceLineDto);
        expenseInvoiceLine = expenseInvoiceLineRepository.insert(expenseInvoiceLine);
        return expenseInvoiceLineMapper.convert(expenseInvoiceLine);
    }

    @Transactional
    public ExpenseInvoiceLineDto update(ExpenseInvoiceLineDto expenseInvoiceLineDto) {
        ExpenseInvoiceLine expenseInvoiceLine = expenseInvoiceLineMapper.convert(expenseInvoiceLineDto);
        expenseInvoiceLine = expenseInvoiceLineRepository.save(expenseInvoiceLine);
        return expenseInvoiceLineMapper.convert(expenseInvoiceLine);
    }

    @Transactional
    public void delete(ObjectId id) {
        Optional<ExpenseInvoiceLine> expenseInvoiceLineOptional = expenseInvoiceLineRepository.findById(id);
        if (expenseInvoiceLineOptional.isPresent()) {
            expenseInvoiceLineRepository.deleteById(id);
        }
        else {
            throw new NotFoundException(ExpenseInvoiceLine.class,id);
        }
    }

    public ExpenseInvoiceLineDto getExpenseInvoiceLine(ObjectId id) {
        Optional<ExpenseInvoiceLine> expenseInvoiceLineOptional =  expenseInvoiceLineRepository.findById(id);
        if (expenseInvoiceLineOptional.isPresent()) {
            return expenseInvoiceLineMapper.convert(expenseInvoiceLineOptional.get());
        }
        else {
            throw new NotFoundException(ExpenseInvoiceLine.class,id);
        }
    }

    public List<ExpenseInvoiceLineDto> findAll() {
        return expenseInvoiceLineRepository.findAll()
                .stream()
                .map(expenseInvoiceLine -> expenseInvoiceLineMapper.convert(expenseInvoiceLine))
                .collect(Collectors.toList());
    }

}
