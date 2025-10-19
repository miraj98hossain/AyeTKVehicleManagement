package com.mhdev.backendservice.controller;

import com.mhdev.backendservice.service.ProductService;
import com.mhdev.commonlib.dto.request.ProductReqDto;
import com.mhdev.commonlib.dto.response.ProductResDto;
import com.mhdev.commonlib.dto.validationGroup.ProductCreateValidation;
import com.mhdev.commonlib.dto.validationGroup.ProductUpdateValidation;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.groups.Default;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResDto> createProduct(@Validated({ProductCreateValidation.class, Default.class}) @RequestBody ProductReqDto productReqDto) {
        try {
            ProductResDto productResDto = productService.createProduct(productReqDto);
            return new ResponseEntity<>(productResDto, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage(),e);
        }
    }
    @PutMapping
    public ResponseEntity<ProductResDto> updateProduct(@Validated({ProductUpdateValidation.class, Default.class}) @RequestBody ProductReqDto productReqDto) {
        try {
            ProductResDto productResDto = productService.updateProduct(productReqDto);
            return new ResponseEntity<>(productResDto, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage(),e);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable(name = "id") Long id) {
        try {
             productService.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductResDto> getProduct(@PathVariable(name = "id") Long id) {
        try {
            ProductResDto productResDto = productService.getProduct(id);
            return new ResponseEntity<>(productResDto,HttpStatus.OK);
        }  catch (EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
        }
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<ProductResDto>> getAllProduct() {
        List<ProductResDto> productResDto = productService.getAllProduct();

        if (productResDto == null || productResDto.isEmpty()) {
            return ResponseEntity.noContent().build();  // Sends 204 without body
        }
        return ResponseEntity.ok(productResDto);
    }


}
