package com.cjcm.spring_boot_cero_a_experto.product.infrastructure.api.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class ProductDto {

    private Long id;
    private String name;
    private String description;
    private double price;
    private String image;
}
