package com.thepolo49.backend.service;

import com.thepolo49.backend.dto.AccountingCodeDto;
import com.thepolo49.backend.exception.NotFoundException;
import com.thepolo49.backend.mapper.AccountingCodeMapper;
import com.thepolo49.backend.model.AccountingCode;
import com.thepolo49.backend.repository.AccountingCodeRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountingCodeService {

    private final AccountingCodeRepository accountingCodeRepository;
    private final AccountingCodeMapper accountingCodeMapper;

    @Transactional
    public AccountingCodeDto create(AccountingCodeDto addressDto) {
        AccountingCode address = accountingCodeMapper.convert(addressDto);
        address = accountingCodeRepository.insert(address);
        return accountingCodeMapper.convert(address);
    }

    @Transactional
    public AccountingCodeDto update(AccountingCodeDto addressDto) {
        AccountingCode address = accountingCodeMapper.convert(addressDto);
        address = accountingCodeRepository.save(address);
        return accountingCodeMapper.convert(address);
    }

    @Transactional
    public void delete(ObjectId id) {
        Optional<AccountingCode> address = accountingCodeRepository.findById(id);
        if (address.isPresent()) {
            accountingCodeRepository.deleteById(id);
        }
        else {
            throw new NotFoundException(AccountingCode.class,id);
        }
    }

    public AccountingCodeDto getAccountingCode(ObjectId id) {
        Optional<AccountingCode> address =  accountingCodeRepository.findById(id);
        if (address.isPresent()) {
            return accountingCodeMapper.convert(address.get());
        }
        else {
            throw new NotFoundException(AccountingCode.class,id);
        }
    }

    public List<AccountingCodeDto> findAll() {
        return accountingCodeRepository.findAll()
                .stream()
                .map(accountingCodeMapper::convert).toList();
    }

}
