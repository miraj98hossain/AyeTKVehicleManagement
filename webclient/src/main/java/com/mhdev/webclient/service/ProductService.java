package com.mhdev.webclient.service;

import com.mhdev.webclient.dto.requestdto.ProductReqDto;
import com.mhdev.webclient.dto.responsedto.ProductResDto;
import com.mhdev.webclient.feignclient.WebServiceProductFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService {
    @Autowired
    private WebServiceProductFeignClient webServiceProductFeignClient;

    public List<ProductResDto> findAll() {
       return webServiceProductFeignClient.getAllProduct();
    }

    public void save(ProductReqDto product) {
        webServiceProductFeignClient.createProduct(product);
    }

    public void deleteById(Long id) {
        webServiceProductFeignClient.deleteProduct(id);
    }

    public ProductResDto findById(Long id) {
        return webServiceProductFeignClient.getProduct(id);
    }
}
