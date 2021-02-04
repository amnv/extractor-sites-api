package com.personal.extractorsitesapi.resourcer;

import com.personal.extractorsitesapi.model.Category;
import com.personal.extractorsitesapi.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping
    public String other() {
        return "OK";
    }
}
