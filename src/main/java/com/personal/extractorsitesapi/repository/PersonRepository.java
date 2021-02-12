package com.personal.extractorsitesapi.repository;

import com.personal.extractorsitesapi.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
