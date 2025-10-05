package com.mhdev.webservice.controller;


import com.mhdev.webservice.dto.requestdto.ProductReqDto;
import com.mhdev.webservice.dto.responsedto.ProductResDto;
import com.mhdev.webservice.dto.validationgroup.ProductCreateValidation;
import com.mhdev.webservice.dto.validationgroup.ProductUpdateValidation;
import com.mhdev.webservice.service.ProductService;
import jakarta.validation.groups.Default;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/product/")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResDto> createProduct(@Validated({ProductCreateValidation.class, Default.class}) @RequestBody ProductReqDto productReqDto) {
        try {
            ProductResDto productResDto = productService.createProduct(productReqDto);
            return new ResponseEntity<>(productResDto, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
    @PutMapping
    public ResponseEntity<ProductResDto> updateProduct(@Validated({ProductUpdateValidation.class, Default.class}) @RequestBody ProductReqDto productReqDto) {
        try {
            ProductResDto productResDto = productService.updateProduct(productReqDto);
            return new ResponseEntity<>(productResDto, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable(name = "id") Long id) {
        try {
             productService.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductResDto> getProduct(@PathVariable(name = "id") Long id) {
        try {
            ProductResDto productResDto = productService.getProduct(id);
            return new ResponseEntity<>(productResDto,HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
    @GetMapping()
    public ResponseEntity<List<ProductResDto>> getProduct() {
        try {
            List<ProductResDto> productResDto = productService.getAllProduct();
            if (productResDto.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(productResDto,HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
}
