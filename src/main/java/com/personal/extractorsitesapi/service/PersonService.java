package com.personal.extractorsitesapi.service;

import com.personal.extractorsitesapi.model.Person;
import com.personal.extractorsitesapi.repository.PersonRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person create(Person person) {
        return this.personRepository.save(person);
    }
    
    public List<Person> findAll() {
        return this.personRepository.findAll();
    }

    public Optional<Person> findById(Long code) {
        return this.personRepository.findById(code);
    }

    public Person update(Long code, Person newPersonData) {
        Optional<Person> personOptional = this.personRepository.findById(code);
        if (personOptional.isPresent()) {
            Person savedPersonData = personOptional.get();
            BeanUtils.copyProperties(newPersonData, savedPersonData, "code");
            return personRepository.save(savedPersonData);
        }

        throw new EmptyResultDataAccessException(1);
    }

    public void updateActive(Long code, Boolean active) {
        Optional<Person> personOptional = this.personRepository.findById(code);

        if (!personOptional.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }

        Person person = personOptional.get();
        person.setActive(active);
        this.personRepository.save(person);
    }


    public void delete(Long code) {
        this.personRepository.deleteById(code);
    }
}
