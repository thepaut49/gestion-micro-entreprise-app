package com.thepolo49.backend.service;

import com.thepolo49.backend.dto.ManagerDto;
import com.thepolo49.backend.exception.NotFoundException;
import com.thepolo49.backend.mapper.ManagerMapper;
import com.thepolo49.backend.model.Manager;
import com.thepolo49.backend.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ManagerService {
    
    private final ManagerRepository managerRepository;
    private final ManagerMapper managerMapper;

    @Transactional
    public ManagerDto create(ManagerDto managerDto) {
        Manager manager = managerMapper.convert(managerDto);
        manager = managerRepository.insert(manager);
        return managerMapper.convert(manager);
    }

    @Transactional
    public ManagerDto update(ManagerDto managerDto) {
        Manager manager = managerMapper.convert(managerDto);
        manager = managerRepository.save(manager);
        return managerMapper.convert(manager);
    }

    @Transactional
    public void delete(ObjectId id) {
        Optional<Manager> manager = managerRepository.findById(id);
        if (manager.isPresent()) {
            managerRepository.deleteById(id);
        }
        else {
            throw new NotFoundException(Manager.class,id);
        }
    }

    public ManagerDto getManager(ObjectId id) {
        Optional<Manager> manager =  managerRepository.findById(id);
        if (manager.isPresent()) {
            return managerMapper.convert(manager.get());
        }
        else {
            throw new NotFoundException(Manager.class,id);
        }
    }

    public List<ManagerDto> findAll() {
        return managerRepository.findAll()
                .stream()
                .map(manager -> managerMapper.convert(manager))
                .collect(Collectors.toList());
    }

}
