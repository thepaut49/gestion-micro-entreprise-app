package com.thepolo49.backend.controller;

import com.thepolo49.backend.dto.ListResponse;

import com.thepolo49.backend.dto.PersonDto;
import com.thepolo49.backend.service.PersonService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@Tag(name = "Person")
@RestController
@RequestMapping(path = "api/persons")
@RolesAllowed({"USER_ADMIN","SIMPLE_USER"})
@RequiredArgsConstructor
public class PersonApi {

    private final PersonService personService;

    @PostMapping
    public PersonDto create(@RequestBody @Valid PersonDto personDto) {
        return personService.create(personDto);
    }

    @PutMapping("{id}")
    public PersonDto update(@RequestBody @Valid PersonDto personDto) {
        return personService.update(personDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        personService.delete(new ObjectId(id));
        return new ResponseEntity<>("Personne supprimée !", HttpStatus.OK);
    }

    @GetMapping("{id}")
    public PersonDto get(@PathVariable String id) {
        return personService.getPerson(new ObjectId(id));
    }

    @GetMapping()
    public ListResponse<PersonDto> findAll() {
        return new ListResponse<>(personService.findAll());
    }

}
