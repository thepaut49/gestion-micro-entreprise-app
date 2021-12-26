package com.thepolo49.backend.controller;

import com.thepolo49.backend.dto.ListResponse;
import com.thepolo49.backend.dto.commons.ContactInfoDto;
import com.thepolo49.backend.service.ContactInfoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@Tag(name = "ContactInfo")
@RestController
@RequestMapping(path = "api/contact-infos")
@RolesAllowed({"USER_ADMIN","SIMPLE_USER"})
@RequiredArgsConstructor
public class ContactInfoApi {

    private final ContactInfoService contactInfoService;

    @PostMapping
    public ContactInfoDto create(@RequestBody @Valid ContactInfoDto contactInfoDto) {
        return contactInfoService.create(contactInfoDto);
    }

    @PutMapping("{id}")
    public ContactInfoDto update(@RequestBody @Valid ContactInfoDto contactInfoDto) {
        return contactInfoService.update(contactInfoDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        contactInfoService.delete(new ObjectId(id));
        return new ResponseEntity<>("Contact infos supprimée !", HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ContactInfoDto get(@PathVariable String id) {
        return contactInfoService.getContactInfo(new ObjectId(id));
    }

    @GetMapping()
    public ListResponse<ContactInfoDto> findAll() {
        return new ListResponse<>(contactInfoService.findAll());
    }

}
