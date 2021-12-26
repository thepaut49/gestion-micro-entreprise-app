package com.thepolo49.backend.controller;

import com.thepolo49.backend.dto.*;

import com.thepolo49.backend.dto.microcompany.MicroCompanyDto;
import com.thepolo49.backend.mapper.MicroCompanyMapper;
import com.thepolo49.backend.mapper.UserViewMapper;
import com.thepolo49.backend.service.MicroCompanyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@Tag(name = "MicroCompany")
@RestController
@RequestMapping(path = "api/micro-companies")
@RolesAllowed({"USER_ADMIN","SIMPLE_USER"})
@RequiredArgsConstructor
public class MicroCompanyApi {

    private final MicroCompanyService microCompanyService;

    @PostMapping
    public MicroCompanyDto create(@RequestBody @Valid MicroCompanyDto microCompanyDto) {
        return microCompanyService.create(microCompanyDto);
    }

    @PutMapping("{id}")
    public MicroCompanyDto update(@RequestBody @Valid MicroCompanyDto microCompanyDto) {
        return microCompanyService.update(microCompanyDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        microCompanyService.delete(new ObjectId(id));
        return new ResponseEntity<>("Micro entreprise supprimée !", HttpStatus.OK);
    }

    @GetMapping("{id}")
    public MicroCompanyDto get(@PathVariable String id) {
        return microCompanyService.getMicroCompany(new ObjectId(id));
    }

    @GetMapping()
    public ListResponse<MicroCompanyDto> findAll() {
        return new ListResponse<>(microCompanyService.findAll());
    }

}
