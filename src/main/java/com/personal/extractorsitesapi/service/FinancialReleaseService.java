package com.personal.extractorsitesapi.service;

import com.personal.extractorsitesapi.model.FinancialRelease;
import com.personal.extractorsitesapi.repository.FinancialReleaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FinancialReleaseService {

    @Autowired
    private FinancialReleaseRepository financialReleaseRepository;

    public List<FinancialRelease> findAll() {
        return this.financialReleaseRepository.findAll();
    }

    public FinancialRelease findById(Long code) {
        Optional<FinancialRelease> financialReleaseOptional = this.financialReleaseRepository.findById(code);
        if (financialReleaseOptional.isPresent())  return financialReleaseOptional.get();

        throw new EmptyResultDataAccessException(1);
    }
}
