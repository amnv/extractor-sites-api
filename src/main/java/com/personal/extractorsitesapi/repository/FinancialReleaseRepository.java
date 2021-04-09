package com.personal.extractorsitesapi.repository;

import com.personal.extractorsitesapi.model.FinancialRelease;
import com.personal.extractorsitesapi.repository.financialReleases.FinancialReleaseRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancialReleaseRepository extends JpaRepository<FinancialRelease, Long>, FinancialReleaseRepositoryQuery {
}
