package com.personal.extractorsitesapi.resource;

import com.personal.extractorsitesapi.event.ResourceBuildEvent;
import com.personal.extractorsitesapi.model.Category;
import com.personal.extractorsitesapi.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryResource {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

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

        publisher.publishEvent(new ResourceBuildEvent(this, response, categorySaved.getCode()));

        return ResponseEntity.status(HttpStatus.CREATED).body(categorySaved);
    }
}
