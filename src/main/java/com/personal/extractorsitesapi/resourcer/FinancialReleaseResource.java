package com.personal.extractorsitesapi.resourcer;

import com.personal.extractorsitesapi.model.FinancialRelease;
import com.personal.extractorsitesapi.service.FinancialReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/finance_releases")
public class FinancialReleaseResource {

    @Autowired
    private FinancialReleaseService financialReleaseService;

    @GetMapping
    public ResponseEntity<List<FinancialRelease>> get() {
        List<FinancialRelease> financialReleases = this.financialReleaseService.findAll();
        return ResponseEntity.ok(financialReleases);
    }

    @GetMapping("/{code}")
    public ResponseEntity<FinancialRelease> findById(@PathVariable Long code) {
        FinancialRelease financialRelease = this.financialReleaseService.findById(code);
        return ResponseEntity.ok(financialRelease);
    }

}
