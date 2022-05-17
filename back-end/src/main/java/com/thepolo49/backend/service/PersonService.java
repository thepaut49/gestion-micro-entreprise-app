package com.thepolo49.backend.service;

import com.thepolo49.backend.dto.PersonDto;
import com.thepolo49.backend.exception.NotFoundException;
import com.thepolo49.backend.mapper.PersonMapper;
import com.thepolo49.backend.model.Person;
import com.thepolo49.backend.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    @Transactional
    public PersonDto create(PersonDto personDto) {
        Person person = personMapper.convert(personDto);
        person = personRepository.insert(person);
        return personMapper.convert(person);
    }

    @Transactional
    public PersonDto update(PersonDto personDto) {
        Person person = personMapper.convert(personDto);
        person = personRepository.save(person);
        return personMapper.convert(person);
    }

    @Transactional
    public void delete(ObjectId id) {
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()) {
            personRepository.deleteById(id);
        }
        else {
            throw new NotFoundException(Person.class,id);
        }
    }

    public PersonDto getPerson(ObjectId id) {
        Optional<Person> person =  personRepository.findById(id);
        if (person.isPresent()) {
            return personMapper.convert(person.get());
        }
        else {
            throw new NotFoundException(Person.class,id);
        }
    }

    public List<PersonDto> findAll() {
        return personRepository.findAll()
                .stream()
                .map(personMapper::convert).toList();
    }

}
