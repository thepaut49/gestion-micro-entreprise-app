package com.thepolo49.backend.service;

import com.thepolo49.backend.dto.MicroCompanyDto;
import com.thepolo49.backend.exception.NotFoundException;
import com.thepolo49.backend.mapper.MicroCompanyMapper;
import com.thepolo49.backend.model.MicroCompany;
import com.thepolo49.backend.model.user.User;
import com.thepolo49.backend.repository.MicroCompanyRepository;
import com.thepolo49.backend.repository.user.UserRepo;
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
    private final UserRepo userRepo;

    @Transactional
    public MicroCompanyDto create(MicroCompanyDto microCompanyDto, String username) {
        MicroCompany microCompany = microCompanyMapper.convert(microCompanyDto);
        Optional<User> user = userRepo.findByUsername(username);
        microCompany.setUser(user.get());
        microCompany = microCompanyRepository.insert(microCompany);
        return microCompanyMapper.convert(microCompany);
    }

    @Transactional
    public MicroCompanyDto update(MicroCompanyDto microCompanyDto,String username) {
        MicroCompany microCompany = microCompanyMapper.convert(microCompanyDto);
        Optional<User> user = userRepo.findByUsername(username);
        microCompany.setUser(user.get());
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

    public List<MicroCompanyDto> getAllMicroForAdmin() {
        List<MicroCompanyDto> microCompanys = microCompanyRepository.findByAccessibleByAdminTrue()
                .stream()
                .map(microCompany -> microCompanyMapper.convert(microCompany))
                .collect(Collectors.toList());
        return microCompanys;
    }

    public boolean canUserCreateMicroCompany(String userName) {
        Optional<User> user = userRepo.findByUsername(userName);
        List<MicroCompany> microCompanys = microCompanyRepository.findByUserId(user.get().getId());
        return microCompanys == null || microCompanys.isEmpty();
    }

    public List<MicroCompanyDto> getAllMicroForUser(String userName) {
        Optional<User> user = userRepo.findByUsername(userName);
        return microCompanyRepository.findByUserId(user.get().getId()).stream()
                .map(microCompany -> microCompanyMapper.convert(microCompany))
                .collect(Collectors.toList());
    }

}
