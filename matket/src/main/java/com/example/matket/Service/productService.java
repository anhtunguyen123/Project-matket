package com.example.matket.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.matket.Entity.Category;
import com.example.matket.Entity.Products;
import com.example.matket.Respository.CategoryRespository;
import com.example.matket.Respository.ProductRespository;

@Service
public class productService {
    @Autowired
    private ProductRespository productRespository;
    @Autowired
    private CategoryRespository categoryRespository;

    public List<Products> getProduct() {
        return productRespository.findAll();
    }

    public Products getProductsById(Long Id) {
        Optional<Products> productbyId = productRespository.findById(Id);
        if (productbyId.isPresent()) {
            return productbyId.get();
        } else {
            throw new RuntimeException("Product with ID  not found");
        }
    }

    public Products createProduct(Products products) {
        return productRespository.save(products);
    }

    public Products updateProducts(Long id, Products products) {
        return productRespository.findById(id).map(existingProduct -> {
            existingProduct.setName(products.getName());
            existingProduct.setPrice(products.getPrice());
            existingProduct.setStockQuantity(products.getStockQuantity());
            existingProduct.setBrand(products.getBrand());
            existingProduct.setStatus(products.getStatus());
            existingProduct.setNotes(products.getNotes());

            if (products.getCategory() != null) {
                Long categoryId = products.getCategory().getId();
                Category category = categoryRespository.findById(categoryId)
                        .orElseThrow();
                existingProduct.setCategory(category);
            }
            return productRespository.save(existingProduct);
        }).orElseThrow();
    }

    public void deleteProducts(Long iD) {
        productRespository.deleteById(iD);
    }

}