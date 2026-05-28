package com.cjcm.spring_boot_cero_a_experto.product.application.query.getById;

import com.cjcm.spring_boot_cero_a_experto.product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class GetProductByIdResponse{

    private Product product;
}
