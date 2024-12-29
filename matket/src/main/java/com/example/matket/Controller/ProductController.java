package com.example.matket.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.matket.Entity.Products;
import com.example.matket.Service.productService;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = { "http://localhost:4200",
        "http://192.168.0.107:4200" }, allowedHeaders = "*", allowCredentials = "true", methods = {
                RequestMethod.GET, RequestMethod.POST,
                RequestMethod.PUT, RequestMethod.DELETE })

public class ProductController {
    @Autowired
    private productService productService;

    @GetMapping("/get/product")
    public ResponseEntity<List<Products>> getProduct() {
        List<Products> Productall = productService.getProduct();
        return ResponseEntity.ok(Productall);
    }

    @GetMapping("/get/product/{id}")
    public ResponseEntity<Products> getProductsById(@PathVariable Long id) {
        Products ProductsById = productService.getProductsById(id);
        return ResponseEntity.ok(ProductsById);
    }

    @PostMapping("/create")
    public String postMethodName(@RequestBody Products products) {
        productService.createProduct(products);
        return "Product added successfully!";
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Map<String, Object>> putMethodName(@PathVariable Long id, @RequestBody Products products) {
        Map<String, Object> respones = new HashMap<>();
        Products productsUpdate = productService.updateProducts(id, products);
        respones.put("message", "Product updated successfully");
        respones.put("data", productsUpdate);
        return ResponseEntity.ok(respones);
    }

    @DeleteMapping("delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProducts(id);
        return "Product delete successfully";
    }
}
