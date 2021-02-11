package com.personal.extractorsitesapi.resourcer;

import com.personal.extractorsitesapi.model.Category;
import com.personal.extractorsitesapi.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryResource {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public List<Category> listAll() {
        return this.categoryRepository.findAll();
    }

    @GetMapping("/{code}")
    public Category findByCode(@PathVariable Long code) {
        return this.categoryRepository.findById(code).get();
    }

    @PostMapping
    public ResponseEntity<Category> create(@Valid @RequestBody Category category, HttpServletResponse response) {
        Category categorySaved = this.categoryRepository.save(category);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri().path("/{code}")
                .buildAndExpand(categorySaved.getCode()).toUri();
        response.setHeader("Location", uri.toASCIIString());

        return ResponseEntity.created(uri).body(categorySaved);
    }
}
