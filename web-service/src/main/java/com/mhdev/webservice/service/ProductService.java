package com.mhdev.webservice.service;

import com.mhdev.commonlib.dto.request.ProductReqDto;
import com.mhdev.commonlib.dto.response.ProductResDto;
import com.mhdev.webservice.feignclient.BackendProductServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class ProductService {
    @Autowired
    private BackendProductServiceFeignClient backendProductService;


    public ProductResDto createProduct(ProductReqDto productReqDto) {
        return backendProductService.createProduct(productReqDto);
    }

    public ProductResDto updateProduct(ProductReqDto productReqDto) {
       return backendProductService.updateProduct(productReqDto);
    }

    public void deleteProduct(Long id) {
        backendProductService.deleteProduct(id);
    }

    public ProductResDto getProduct(Long id) {
        return backendProductService.getProduct(id);
    }

    public List<ProductResDto> getAllProduct() {
        return backendProductService.getAllProduct();
    }
}
