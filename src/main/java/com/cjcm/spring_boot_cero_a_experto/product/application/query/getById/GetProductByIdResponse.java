package com.cjcm.spring_boot_cero_a_experto.product.application.query;

import com.cjcm.spring_boot_cero_a_experto.product.domain.Product;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetProductByIdResponse{

    private Product product;
}
