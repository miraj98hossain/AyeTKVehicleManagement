package com.mhdev.backendservice.dto.responsedto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class ProductResDto {
    private Long id;
    private String name;
    private Double price;
}
