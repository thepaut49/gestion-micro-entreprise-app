package com.thepolo49.backend.controller;

import com.thepolo49.backend.dto.ListResponse;

import com.thepolo49.backend.dto.commons.AddressDto;
import com.thepolo49.backend.service.AddressService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@Tag(name = "Address")
@RestController
@RequestMapping(path = "api/addresses")
@RolesAllowed({"USER_ADMIN","SIMPLE_USER"})
@RequiredArgsConstructor
public class AddressApi {

    private final AddressService addressService;

    @PostMapping
    public AddressDto create(@RequestBody @Valid AddressDto addressDto) {
        return addressService.create(addressDto);
    }

    @PutMapping("{id}")
    public AddressDto update(@RequestBody @Valid AddressDto addressDto) {
        return addressService.update(addressDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        addressService.delete(new ObjectId(id));
        return new ResponseEntity<>("Adresse supprimée !", HttpStatus.OK);
    }

    @GetMapping("{id}")
    public AddressDto get(@PathVariable String id) {
        return addressService.getAddress(new ObjectId(id));
    }

    @GetMapping()
    public ListResponse<AddressDto> findAll() {
        return new ListResponse<>(addressService.findAll());
    }

}
