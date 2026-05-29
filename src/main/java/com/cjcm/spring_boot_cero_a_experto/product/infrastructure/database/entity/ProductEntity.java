package com.cjcm.spring_boot_cero_a_experto.product.infrastructure.database.entity;

import lombok.Data;

@Data
public class ProductEntity {
    private Long id;
    private String name;
    private String description;
    private double price;
    private String image;
}
