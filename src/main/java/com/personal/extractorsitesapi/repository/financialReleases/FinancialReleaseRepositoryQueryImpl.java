package com.personal.extractorsitesapi.repository.financialReleases;

import com.personal.extractorsitesapi.model.FinancialRelease;
import com.personal.extractorsitesapi.model.FinancialRelease_;
import com.personal.extractorsitesapi.repository.filter.FinancialReleasesFilter;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FinancialReleaseRepositoryQueryImpl implements FinancialReleaseRepositoryQuery {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<FinancialRelease> filter(FinancialReleasesFilter financialReleasesFilter) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<FinancialRelease> criteria = builder.createQuery(FinancialRelease.class);
        Root<FinancialRelease> root = criteria.from(FinancialRelease.class);

        // Create restrictions
        Predicate[] predicates = buildPredicates(financialReleasesFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<FinancialRelease> query = entityManager.createQuery(criteria);
        return query.getResultList();
    }

    private Predicate[] buildPredicates(FinancialReleasesFilter financialReleasesFilter, CriteriaBuilder builder, Root<FinancialRelease> root) {
        List<Predicate> predicates = new ArrayList<>();


        if (!StringUtils.isEmpty(financialReleasesFilter.getDescription())) {
            predicates.add(builder.like(
                builder.lower(root.get(FinancialRelease_.description)),
                    "%" + financialReleasesFilter.getDescription().toLowerCase() + "%"
            ));
        }

        if (financialReleasesFilter.getDueDateFrom() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get(FinancialRelease_.dueDate), financialReleasesFilter.getDueDateFrom()));
        }

        if (financialReleasesFilter.getDueDateTo() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get(FinancialRelease_.dueDate), financialReleasesFilter.getDueDateTo()));
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }
}
