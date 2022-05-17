package com.thepolo49.backend.service.invoice.expense;


import com.thepolo49.backend.dto.invoice.expense.ExpenseInvoiceDto;
import com.thepolo49.backend.exception.NotFoundException;

import com.thepolo49.backend.mapper.invoice.expense.ExpenseInvoiceMapper;
import com.thepolo49.backend.model.MicroCompany;
import com.thepolo49.backend.model.invoice.expense.ExpenseInvoice;
import com.thepolo49.backend.model.user.User;
import com.thepolo49.backend.repository.MicroCompanyRepository;
import com.thepolo49.backend.repository.invoice.expense.ExpenseInvoiceRepository;
import com.thepolo49.backend.repository.user.UserRepo;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExpenseInvoiceService {

    private final ExpenseInvoiceRepository expenseInvoiceRepository;
    private final ExpenseInvoiceMapper expenseInvoiceMapper;
    private final MicroCompanyRepository microCompanyRepository;
    private final UserRepo userRepo;

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

    public List<ExpenseInvoiceDto> findMyExpense(String username) {
        Optional<User> user = userRepo.findByUsername(username);
        if (user.isEmpty()) {
            throw new NotFoundException("User " + username + " not found");
        }

        Optional<MicroCompany> microCompanyOptional = microCompanyRepository.findByUserId(user.get().getId()).stream().findFirst();
        if (microCompanyOptional.isEmpty()) {
            throw new NotFoundException("Micro company not found");
        }

        return expenseInvoiceRepository.findByMicroCompanyId(microCompanyOptional.get().getId())
                .stream()
                .map(expenseInvoiceMapper::convert)
                .toList();

    }
}
