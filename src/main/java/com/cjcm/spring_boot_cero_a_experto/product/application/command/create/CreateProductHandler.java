package com.cjcm.spring_boot_cero_a_experto.product.application.command.create;

import ch.qos.logback.core.util.StringUtil;
import com.cjcm.spring_boot_cero_a_experto.common.mediator.RequestHandler;
import com.cjcm.spring_boot_cero_a_experto.product.domain.entity.Product;
import com.cjcm.spring_boot_cero_a_experto.product.domain.port.ProductRepository;
import com.cjcm.spring_boot_cero_a_experto.util.FileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class CreateProductHandler implements RequestHandler<CreateProductRequest, Void> {


    private final ProductRepository productRepository;
    private final FileUtils fileUtils;

    @Override
    public Void handle(CreateProductRequest request) {

        log.info("Creating product with id {}", request.getId());

        String uniqueFileName = fileUtils.saveProductImage(request.getFile());

        Product product = Product.builder()
                .id(request.getId())
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .image(uniqueFileName)
                .build();

        productRepository.upsert(product);

        log.info("Product with id {} has been created", request.getId());

        return null;
    }

    @Override
    public Class<CreateProductRequest> getRequestType() {
        return CreateProductRequest.class;
    }
}
