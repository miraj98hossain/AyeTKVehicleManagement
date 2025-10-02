package com.mhdev.crud.service;

import com.mhdev.crud.entity.Product;
import com.mhdev.crud.repository.ProductRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    public List<Product> findAll() {
       return productRepo.findAll();
    }

    public void save(Product product) {
        productRepo.save(product);
    }

    public void deleteById(Long id) {
        productRepo.deleteById(id);
    }

    public Product findById(Long id) {
        var p = productRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        return productRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Product not found")
        );
    }
}
