package com.cjcm.spring_boot_cero_a_experto.product.application.command.delete;

import com.cjcm.spring_boot_cero_a_experto.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteProductRequest implements Request<Void> {

    private Long id;
}
