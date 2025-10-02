package com.mhdev.crud.repository;

import com.mhdev.crud.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {
}
