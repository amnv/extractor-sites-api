package com.personal.extractorsitesapi.service;

import com.personal.extractorsitesapi.model.FinancialRelease;
import com.personal.extractorsitesapi.model.Person;
import com.personal.extractorsitesapi.repository.FinancialReleaseRepository;
import com.personal.extractorsitesapi.repository.PersonRepository;
import com.personal.extractorsitesapi.repository.filter.FinancialReleasesFilter;
import com.personal.extractorsitesapi.service.exception.PersonNotPresentOrNotActiveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FinancialReleaseService {

    @Autowired
    private FinancialReleaseRepository financialReleaseRepository;

    @Autowired
    private PersonRepository personRepository;

    public FinancialRelease create(FinancialRelease financialRelease) {
        Optional<Person> person = personRepository.findById(financialRelease.getPerson().getCode());
        if(!person.isPresent() || !person.get().getActive()) {
            throw new PersonNotPresentOrNotActiveException();
        }
        return this.financialReleaseRepository.save(financialRelease);
    }

    public List<FinancialRelease> findAll() {
        return this.financialReleaseRepository.findAll();
    }

    public FinancialRelease findById(Long code) {
        Optional<FinancialRelease> financialReleaseOptional = this.financialReleaseRepository.findById(code);
        if (financialReleaseOptional.isPresent())  return financialReleaseOptional.get();

        throw new EmptyResultDataAccessException(1);
    }

    public List<FinancialRelease> filter(FinancialReleasesFilter financialReleasesFilter) {
        return this.financialReleaseRepository.filter(financialReleasesFilter);
    }
}
