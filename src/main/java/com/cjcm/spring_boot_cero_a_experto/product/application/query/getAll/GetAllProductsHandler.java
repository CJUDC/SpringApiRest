package com.cjcm.spring_boot_cero_a_experto.product.application.query.getAll;

import com.cjcm.spring_boot_cero_a_experto.common.mediator.RequestHandler;
import com.cjcm.spring_boot_cero_a_experto.product.domain.entity.Product;
import com.cjcm.spring_boot_cero_a_experto.product.domain.port.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetAllProductsHandler implements RequestHandler<GetAllProductsRequest, GetAllProductsResponse> {

    private final ProductRepository productRepository;

    @Override
    public GetAllProductsResponse handle(GetAllProductsRequest request) {

        log.info("Getting all products on Application layer");

        List<Product> products = productRepository.findAll();

        log.info("Found {} products", products.size());

        return new GetAllProductsResponse(products);
    }

    @Override
    public Class<GetAllProductsRequest> getRequestType() {
        return GetAllProductsRequest.class;
    }
}
