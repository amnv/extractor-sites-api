package com.personal.extractorsitesapi.resource;

import com.personal.extractorsitesapi.event.ResourceBuildEvent;
import com.personal.extractorsitesapi.model.Person;
import com.personal.extractorsitesapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonResource {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private PersonService personService;

    @PostMapping
    public ResponseEntity<Person> create(@Valid @RequestBody Person person, HttpServletResponse response) {
        Person personCreated = this.personService.create(person);
        publisher.publishEvent(new ResourceBuildEvent(this, response, personCreated.getCode()));

        return ResponseEntity.status(HttpStatus.CREATED).body(personCreated);
    }

    @GetMapping
    public List<Person> listAll() {
        return this.personService.findAll();
    }

    @GetMapping("/{code}")
    public ResponseEntity<Person> read(@PathVariable Long code) {
        Optional<Person> personOptional = this.personService.findById(code);
        return personOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{code}")
    public ResponseEntity<Person> update(@PathVariable Long code, @RequestBody @Valid Person person) {
        Person personUpdated = this.personService.update(code, person);
        return ResponseEntity.ok(personUpdated);
    }

    @PutMapping("/{code}/active")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateActive(@PathVariable Long code, @RequestBody Boolean active) {
        this.personService.updateActive(code, active);
    }

    @DeleteMapping("/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long code) {
        this.personService.delete(code);
    }
}
