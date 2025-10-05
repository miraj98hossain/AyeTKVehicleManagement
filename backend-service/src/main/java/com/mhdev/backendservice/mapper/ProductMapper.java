package com.mhdev.backendservice.mapper;

import com.mhdev.backendservice.dto.requestdto.ProductReqDto;
import com.mhdev.backendservice.dto.responsedto.ProductResDto;
import com.mhdev.backendservice.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toProduct(ProductReqDto productDto);
    ProductResDto toProductResDto(Product product);
}
