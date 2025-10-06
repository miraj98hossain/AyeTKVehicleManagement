package com.mhdev.webclient.feignclient;

import com.mhdev.webclient.dto.requestdto.ProductReqDto;
import com.mhdev.webclient.dto.responsedto.ProductResDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(name = "WebServiceProductFeignClient",url = "${web.service.url}${web.service.product.prefix}")
public interface WebServiceProductFeignClient {
    @PostMapping
    ProductResDto createProduct(@RequestBody ProductReqDto productReqDto);
    @PutMapping
    ProductResDto updateProduct(@RequestBody ProductReqDto productReqDto);
    @DeleteMapping("/{id}")
     void deleteProduct(@PathVariable(name = "id") Long id);
    @GetMapping("/{id}")
    ProductResDto getProduct(@PathVariable(name = "id") Long id) ;
    @GetMapping("/getAll")
    List<ProductResDto> getAllProduct();
}
