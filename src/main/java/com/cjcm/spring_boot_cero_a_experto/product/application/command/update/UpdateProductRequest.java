package com.cjcm.spring_boot_cero_a_experto.product.application.command.update;

import com.cjcm.spring_boot_cero_a_experto.common.mediator.Request;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UpdateProductRequest implements Request<Void> {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private MultipartFile File;

}
