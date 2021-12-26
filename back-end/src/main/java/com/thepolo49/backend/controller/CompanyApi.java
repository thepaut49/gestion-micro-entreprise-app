package com.thepolo49.backend.controller;

import com.thepolo49.backend.dto.ListResponse;
import com.thepolo49.backend.dto.company.CompanyDto;
import com.thepolo49.backend.service.CompanyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@Tag(name = "Company")
@RestController
@RequestMapping(path = "api/companies")
@RolesAllowed({"USER_ADMIN","SIMPLE_USER"})
@RequiredArgsConstructor
public class CompanyApi {

    private final CompanyService companyService;

    @PostMapping
    public CompanyDto create(@RequestBody @Valid CompanyDto companyDto) {
        return companyService.create(companyDto);
    }

    @PutMapping("{id}")
    public CompanyDto update(@RequestBody @Valid CompanyDto companyDto) {
        return companyService.update(companyDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        companyService.delete(new ObjectId(id));
        return new ResponseEntity<>("Entreprise supprimée !", HttpStatus.OK);
    }

    @GetMapping("{id}")
    public CompanyDto get(@PathVariable String id) {
        return companyService.getCompany(new ObjectId(id));
    }

    @GetMapping()
    public ListResponse<CompanyDto> findAll() {
        return new ListResponse<>(companyService.findAll());
    }

}
