package com.personal.extractorsitesapi.resource;

import com.personal.extractorsitesapi.event.ResourceBuildEvent;
import com.personal.extractorsitesapi.model.FinancialRelease;
import com.personal.extractorsitesapi.repository.filter.FinancialReleasesFilter;
import com.personal.extractorsitesapi.service.FinancialReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/finance_releases")
public class FinancialReleaseResource {

    @Autowired
    private FinancialReleaseService financialReleaseService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping
    public ResponseEntity<FinancialRelease> create(@Valid @RequestBody FinancialRelease financialRelease, HttpServletResponse response) {
        FinancialRelease savedFinancialRelease = this.financialReleaseService.create(financialRelease);

        this.publisher.publishEvent(new ResourceBuildEvent(this, response, savedFinancialRelease.getCode()));

        return ResponseEntity.status(HttpStatus.CREATED).body(savedFinancialRelease);
    }

    @GetMapping
    public ResponseEntity<List<FinancialRelease>> get(FinancialReleasesFilter financialReleasesFilter) {
        List<FinancialRelease> financialReleases = this.financialReleaseService.filter(financialReleasesFilter);
        return ResponseEntity.ok(financialReleases);
    }

    @GetMapping("/{code}")
    public ResponseEntity<FinancialRelease> findById(@PathVariable Long code) {
        FinancialRelease financialRelease = this.financialReleaseService.findById(code);
        return ResponseEntity.ok(financialRelease);
    }

}
