package com.example.springsecurityapplication.repositories;

import com.example.springsecurityapplication.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository <Category, Integer> {
    Category findByName(String name);
}
