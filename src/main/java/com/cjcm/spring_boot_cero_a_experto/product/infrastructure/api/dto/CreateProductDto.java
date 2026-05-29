package com.cjcm.spring_boot_cero_a_experto.product.infrastructure.api.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CreateProductDto {

    private Long id;
    @NotBlank
    private String name;
    @Length(min = 10, max = 255, message = "Description must be between 10 and 255 characters")
    private String description;
    @DecimalMin(value = "0.01", inclusive = false, message = "Price must be greater than 0.01")
    @DecimalMax(value = "999.99", inclusive = true, message = "Price must be less than or equal to 9999.99")
    private double price;
    private MultipartFile file;
}
