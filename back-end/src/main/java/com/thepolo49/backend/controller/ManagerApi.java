package com.thepolo49.backend.controller;

import com.thepolo49.backend.dto.ListResponse;
import com.thepolo49.backend.dto.manager.ManagerDto;
import com.thepolo49.backend.service.ManagerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@Tag(name = "Manager")
@RestController
@RequestMapping(path = "api/managers")
@RolesAllowed({"USER_ADMIN","SIMPLE_USER"})
@RequiredArgsConstructor
public class ManagerApi {

    private final ManagerService managerService;

    @PostMapping
    public ManagerDto create(@RequestBody @Valid ManagerDto managerDto) {
        return managerService.create(managerDto);
    }

    @PutMapping("{id}")
    public ManagerDto update(@RequestBody @Valid ManagerDto managerDto) {
        return managerService.update(managerDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        managerService.delete(new ObjectId(id));
        return new ResponseEntity<>("Manager supprimée !", HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ManagerDto get(@PathVariable String id) {
        return managerService.getManager(new ObjectId(id));
    }

    @GetMapping()
    public ListResponse<ManagerDto> findAll() {
        return new ListResponse<>(managerService.findAll());
    }

}
