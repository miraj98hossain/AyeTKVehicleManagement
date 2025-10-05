package com.mhdev.backendservice.repository;

import com.mhdev.backendservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
