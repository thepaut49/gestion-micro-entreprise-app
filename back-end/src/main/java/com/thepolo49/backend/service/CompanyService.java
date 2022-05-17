package com.thepolo49.backend.service;

import com.thepolo49.backend.dto.CompanyDto;
import com.thepolo49.backend.exception.NotFoundException;
import com.thepolo49.backend.mapper.CompanyMapper;
import com.thepolo49.backend.model.Company;
import com.thepolo49.backend.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    @Transactional
    public CompanyDto create(CompanyDto companyDto) {
        Company company = companyMapper.convert(companyDto);
        company = companyRepository.insert(company);
        return companyMapper.convert(company);
    }

    @Transactional
    public CompanyDto update(CompanyDto companyDto) {
        Company company = companyMapper.convert(companyDto);
        company = companyRepository.save(company);
        return companyMapper.convert(company);
    }

    @Transactional
    public void delete(ObjectId id) {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isPresent()) {
            companyRepository.deleteById(id);
        }
        else {
            throw new NotFoundException(Company.class,id);
        }
    }

    public CompanyDto getCompany(ObjectId id) {
        Optional<Company> company =  companyRepository.findById(id);
        if (company.isPresent()) {
            return companyMapper.convert(company.get());
        }
        else {
            throw new NotFoundException(Company.class,id);
        }
    }

    public List<CompanyDto> findAll() {
        return companyRepository.findAll()
                .stream()
                .map(companyMapper::convert)
                .toList();
    }

}
