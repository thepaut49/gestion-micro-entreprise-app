package com.thepolo49.backend.service;

import com.thepolo49.backend.dto.commons.ContactInfoDto;
import com.thepolo49.backend.exception.NotFoundException;
import com.thepolo49.backend.mapper.ContactInfoMapper;
import com.thepolo49.backend.model.ContactInfo;
import com.thepolo49.backend.repository.ContactInfoRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactInfoService {

    private final ContactInfoRepository contactInfoRepository;
    private final ContactInfoMapper contactInfoMapper;

    @Transactional
    public ContactInfoDto create(ContactInfoDto contactInfoDto) {
        ContactInfo contactInfo = contactInfoMapper.convert(contactInfoDto);
        contactInfo = contactInfoRepository.insert(contactInfo);
        return contactInfoMapper.convert(contactInfo);
    }

    @Transactional
    public ContactInfoDto update(ContactInfoDto contactInfoDto) {
        ContactInfo contactInfo = contactInfoMapper.convert(contactInfoDto);
        contactInfo = contactInfoRepository.save(contactInfo);
        return contactInfoMapper.convert(contactInfo);
    }

    @Transactional
    public void delete(ObjectId id) {
        Optional<ContactInfo> contactInfo = contactInfoRepository.findById(id);
        if (contactInfo.isPresent()) {
            contactInfoRepository.deleteById(id);
        }
        else {
            throw new NotFoundException(ContactInfo.class,id);
        }
    }

    public ContactInfoDto getContactInfo(ObjectId id) {
        Optional<ContactInfo> contactInfo =  contactInfoRepository.findById(id);
        if (contactInfo.isPresent()) {
            return contactInfoMapper.convert(contactInfo.get());
        }
        else {
            throw new NotFoundException(ContactInfo.class,id);
        }
    }

    public List<ContactInfoDto> findAll() {
        return contactInfoRepository.findAll()
                .stream()
                .map(contactInfo -> contactInfoMapper.convert(contactInfo))
                .collect(Collectors.toList());
    }

}
