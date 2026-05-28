package com.cjcm.spring_boot_cero_a_experto.product.application.query;

import com.cjcm.spring_boot_cero_a_experto.common.mediator.Request;

import lombok.Data;

@Data
public class GetProductByIdRequest implements Request<GetProductByIdResponse> {

    private Long id;
}
