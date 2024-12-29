package com.example.matket.Respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.matket.Entity.Products;

public interface ProductRespository extends JpaRepository<Products, Long> {

}
