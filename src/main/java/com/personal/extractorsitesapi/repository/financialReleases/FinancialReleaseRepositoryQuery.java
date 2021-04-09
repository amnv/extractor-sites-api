package com.personal.extractorsitesapi.repository.financialReleases;

import com.personal.extractorsitesapi.model.FinancialRelease;
import com.personal.extractorsitesapi.repository.filter.FinancialReleasesFilter;

import java.util.List;

public interface FinancialReleaseRepositoryQuery {
    List<FinancialRelease> filter(FinancialReleasesFilter financialReleasesFilter);
}
