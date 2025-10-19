package com.mhdev.backendservice.service;

import com.mhdev.backendservice.entity.Product;
import com.mhdev.backendservice.mapper.ProductMapper;
import com.mhdev.backendservice.repository.ProductRepository;
import com.mhdev.commonlib.dto.request.ProductReqDto;
import com.mhdev.commonlib.dto.response.ProductResDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;
    @Transactional
    public ProductResDto createProduct(ProductReqDto productReqDto) {
        Product product = new Product();
        product = productMapper.toProduct(productReqDto);
       return productMapper.toProductResDto( productRepository.save(product));
    }
    @Transactional
    public ProductResDto updateProduct(ProductReqDto productReqDto) {
        Product product = productRepository.findById(productReqDto.getId()).orElseThrow(
                ()-> new EntityNotFoundException("Product not found with id: " + productReqDto.getId())
        );
        product.setName(productReqDto.getName());
        product.setPrice(productReqDto.getPrice());
        return productMapper.toProductResDto( productRepository.save(product));
    }
    @Transactional
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("Product not found with id: " + id)
        );
        productRepository.delete(product);
    }
    @Transactional(readOnly = true)
    public ProductResDto getProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("Product not found with id: " + id)
        );
        return productMapper.toProductResDto(product);
    }
    @Transactional(readOnly = true)
    public List<ProductResDto> getAllProduct() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(productMapper::toProductResDto).collect(Collectors.toList());
    }
}
