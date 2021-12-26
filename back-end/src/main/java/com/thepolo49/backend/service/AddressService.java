package com.thepolo49.backend.service;

import com.thepolo49.backend.dto.commons.AddressDto;
import com.thepolo49.backend.exception.NotFoundException;
import com.thepolo49.backend.mapper.AddressMapper;
import com.thepolo49.backend.model.Address;
import com.thepolo49.backend.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    @Transactional
    public AddressDto create(AddressDto addressDto) {
        Address address = addressMapper.convert(addressDto);
        address = addressRepository.insert(address);
        return addressMapper.convert(address);
    }

    @Transactional
    public AddressDto update(AddressDto addressDto) {
        Address address = addressMapper.convert(addressDto);
        address = addressRepository.save(address);
        return addressMapper.convert(address);
    }

    @Transactional
    public void delete(ObjectId id) {
        Optional<Address> address = addressRepository.findById(id);
        if (address.isPresent()) {
            addressRepository.deleteById(id);
        }
        else {
            throw new NotFoundException(Address.class,id);
        }
    }

    public AddressDto getAddress(ObjectId id) {
        Optional<Address> address =  addressRepository.findById(id);
        if (address.isPresent()) {
            return addressMapper.convert(address.get());
        }
        else {
            throw new NotFoundException(Address.class,id);
        }
    }

    public List<AddressDto> findAll() {
        return addressRepository.findAll()
                .stream()
                .map(address -> addressMapper.convert(address))
                .collect(Collectors.toList());
    }

}
