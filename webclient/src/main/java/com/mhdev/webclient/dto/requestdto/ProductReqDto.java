package com.mhdev.webclient.dto.requestdto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductReqDto {
    @NotNull(message = "Product Id cannot be null")
    private Long id;
    @NotBlank(message = "Product name is required")
    private String name;
    @DecimalMin(value = "0.0",inclusive = false,message = "Product price should be greater than zero")
    private Double price;
}
