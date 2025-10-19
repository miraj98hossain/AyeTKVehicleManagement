package com.mhdev.commonlib.dto.request;


import com.mhdev.commonlib.dto.validationGroup.ProductCreateValidation;
import com.mhdev.commonlib.dto.validationGroup.ProductUpdateValidation;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductReqDto {
    @NotNull(groups = {ProductUpdateValidation.class}, message = "Product Id cannot be null")
    private Long id;
    @NotBlank(groups = {ProductUpdateValidation.class, ProductCreateValidation.class},message = "Product name is required")
    private String name;
    @DecimalMin(groups = {ProductUpdateValidation.class, ProductCreateValidation.class},value = "0.0",inclusive = false,message = "Product price should be greater than zero")
    private Double price;
}
