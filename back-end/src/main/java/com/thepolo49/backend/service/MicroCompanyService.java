package com.thepolo49.backend.service;

import com.googlecode.jmapper.JMapper;
import com.thepolo49.backend.dto.microcompany.MicroCompanyDto;
import com.thepolo49.backend.exception.NotFoundException;
import com.thepolo49.backend.mapper.MicroCompanyMapper;
import com.thepolo49.backend.model.MicroCompany;
import com.thepolo49.backend.repository.MicroCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class MicroCompanyService {

    private final MicroCompanyRepository microCompanyRepository;
    private final MicroCompanyMapper microCompanyMapper;

    @Transactional
    public MicroCompanyDto create(MicroCompanyDto microCompanyDto) {
        MicroCompany microCompany = microCompanyMapper.convert(microCompanyDto);
        microCompany = microCompanyRepository.insert(microCompany);
        return microCompanyMapper.convert(microCompany);
    }

    @Transactional
    public MicroCompanyDto update(MicroCompanyDto microCompanyDto) {
        MicroCompany microCompany = microCompanyMapper.convert(microCompanyDto);
        microCompany = microCompanyRepository.save(microCompany);
        return microCompanyMapper.convert(microCompany);
    }


    @Transactional
    public void delete(ObjectId id) {
        Optional<MicroCompany> microCompany = microCompanyRepository.findById(id);
        if (microCompany.isPresent()) {
            microCompanyRepository.deleteById(id);
        }
        else {
            throw new NotFoundException(MicroCompany.class,id);
        }
    }

    public MicroCompanyDto getMicroCompany(ObjectId id) {
        Optional<MicroCompany> microCompany =  microCompanyRepository.findById(id);
        if (microCompany.isPresent()) {
            return microCompanyMapper.convert(microCompany.get());
        }
        else {
            throw new NotFoundException(MicroCompany.class,id);
        }
    }

    public List<MicroCompanyDto> findAll() {
        List<MicroCompanyDto> microCompanys = microCompanyRepository.findAll()
                .stream()
                .map(microCompany -> microCompanyMapper.convert(microCompany))
                .collect(Collectors.toList());
        return microCompanys;
    }


}