package com.example.matket.Respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.matket.Entity.Category;

public interface CategoryRespository extends JpaRepository<Category, Long> {
    Optional<Category> findById(Long id);
}
