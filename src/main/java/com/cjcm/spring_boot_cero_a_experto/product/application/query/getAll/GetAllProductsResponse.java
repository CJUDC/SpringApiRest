package com.cjcm.spring_boot_cero_a_experto.product.application.query.getAll;

import com.cjcm.spring_boot_cero_a_experto.product.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class GetAllProductsResponse {

    private List<Product> products;
}
