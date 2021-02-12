package com.personal.extractorsitesapi.resourcer;

import com.personal.extractorsitesapi.model.Person;
import com.personal.extractorsitesapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonResource {
    @Autowired
    private PersonRepository personRepository;

    @PostMapping
    public ResponseEntity<Person> create(@Valid @RequestBody Person person, HttpServletResponse response) {
        Person personCreated = this.personRepository.save(person);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri().path("/{code}")
                .buildAndExpand(personCreated.getCode()).toUri();
        response.setHeader("Location", uri.toASCIIString());

        return ResponseEntity.created(uri).body(personCreated);
    }

    @GetMapping
    public List<Person> listAll() {
        return this.personRepository.findAll();
    }

    @GetMapping("/{code}")
    public Person read(@PathVariable Long code) {
        return this.personRepository.findById(code).get();
    }
}
