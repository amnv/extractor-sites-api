package com.personal.extractorsitesapi.resourcer;

import com.personal.extractorsitesapi.event.ResourceBuildEvent;
import com.personal.extractorsitesapi.model.Person;
import com.personal.extractorsitesapi.repository.PersonRepository;
import com.personal.extractorsitesapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonResource {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private PersonService personService;

    @PostMapping
    public ResponseEntity<Person> create(@Valid @RequestBody Person person, HttpServletResponse response) {
        Person personCreated = this.personRepository.save(person);
        publisher.publishEvent(new ResourceBuildEvent(this, response, personCreated.getCode()));

        return ResponseEntity.status(HttpStatus.CREATED).body(personCreated);
    }

    @GetMapping
    public List<Person> listAll() {
        return this.personRepository.findAll();
    }

    @GetMapping("/{code}")
    public Person read(@PathVariable Long code) {
        return this.personRepository.findById(code).get();
    }

    @DeleteMapping("/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long code) {
        this.personRepository.deleteById(code);
    }

    @PutMapping("/{code}")
    public ResponseEntity<Person> update(@PathVariable Long code, @RequestBody @Valid Person person) {
        Person personUpdated = this.personService.update(code, person);
        return ResponseEntity.ok(personUpdated);
    }
}
