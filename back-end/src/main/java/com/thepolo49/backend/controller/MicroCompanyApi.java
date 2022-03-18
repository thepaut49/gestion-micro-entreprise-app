package com.thepolo49.backend.controller;

import com.thepolo49.backend.dto.*;

import com.thepolo49.backend.dto.MicroCompanyDto;
import com.thepolo49.backend.service.MicroCompanyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@Tag(name = "MicroCompany")
@RestController
@RequestMapping(path = "api/micro-companies")
@RolesAllowed({"USER_ADMIN","SIMPLE_USER"})
@RequiredArgsConstructor
public class MicroCompanyApi {

    private final MicroCompanyService microCompanyService;

    @PostMapping
    public MicroCompanyDto create(@RequestBody @Valid MicroCompanyDto microCompanyDto) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return microCompanyService.create(microCompanyDto, userDetails.getUsername());
    }

    @PutMapping("{id}")
    public MicroCompanyDto update(@RequestBody @Valid MicroCompanyDto microCompanyDto) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return microCompanyService.update(microCompanyDto, userDetails.getUsername());
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

    @GetMapping("getAllMicroForAdmin")
    @RolesAllowed({"USER_ADMIN"})
    public ListResponse<MicroCompanyDto> getAllMicroForAdmin() {
        return new ListResponse<>(microCompanyService.getAllMicroForAdmin());
    }

    @GetMapping("canUserCreateMicroCompany")
    public boolean canUserCreateMicroCompany() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return microCompanyService.canUserCreateMicroCompany(userDetails.getUsername());
    }

    @GetMapping("getAllMicroForUser")
    public ListResponse<MicroCompanyDto> getAllMicroForUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return new ListResponse<>(microCompanyService.getAllMicroForUser(userDetails.getUsername()));
    }

    @GetMapping("me")
    public MicroCompanyDto getMyMicroCompany() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return microCompanyService.getMyMicroCompany(userDetails.getUsername());
    }

}
