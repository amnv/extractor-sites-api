package com.personal.extractorsitesapi.service;

import com.personal.extractorsitesapi.model.Person;
import com.personal.extractorsitesapi.repository.PersonRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public Person update(Long code, Person newPersonData) {
        Optional<Person> personOptional = this.personRepository.findById(code);
        if (personOptional.isPresent()) {
            Person savedPersonData = personOptional.get();
            BeanUtils.copyProperties(newPersonData, savedPersonData, "code");
            return personRepository.save(savedPersonData);
        }

        throw new EmptyResultDataAccessException(1);
    }

    public Person get(Long code) {
        return this.personRepository.findById(code).get();
    }

}
