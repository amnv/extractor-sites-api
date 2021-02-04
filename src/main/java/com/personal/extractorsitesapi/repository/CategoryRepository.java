package com.personal.extractorsitesapi.repository;

import com.personal.extractorsitesapi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
