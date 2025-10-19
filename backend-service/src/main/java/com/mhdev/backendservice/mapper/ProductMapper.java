package com.mhdev.backendservice.mapper;

import com.mhdev.backendservice.entity.Product;
import com.mhdev.commonlib.dto.request.ProductReqDto;
import com.mhdev.commonlib.dto.response.ProductResDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toProduct(ProductReqDto productDto);
    ProductResDto toProductResDto(Product product);
}
