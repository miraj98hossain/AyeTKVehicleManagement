package com.mhdev.webservice.feignclient;

import com.mhdev.commonlib.dto.request.ProductReqDto;
import com.mhdev.commonlib.dto.response.ProductResDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@FeignClient(name = "BackendProductServiceFeignClient",url = "${backend.service.url}${backend.service.product.prefix}")
public interface BackendProductServiceFeignClient {
    @PostMapping
    ProductResDto createProduct(@RequestBody ProductReqDto productReqDto);
    @PutMapping
    ProductResDto updateProduct(@RequestBody ProductReqDto productReqDto);
    @DeleteMapping("/{id}")
     void deleteProduct(@PathVariable(name = "id") Long id);
    @GetMapping("/{id}")
    ProductResDto getProduct(@PathVariable(name = "id") Long id) ;
    @GetMapping("/getAll")
    List<ProductResDto> getAllProduct() ;
}
