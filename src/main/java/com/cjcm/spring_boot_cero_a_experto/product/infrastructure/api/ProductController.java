package com.cjcm.spring_boot_cero_a_experto.product.infrastructure.api;

import com.cjcm.spring_boot_cero_a_experto.common.mediator.Mediator;
import com.cjcm.spring_boot_cero_a_experto.product.application.command.create.CreateProductRequest;
import com.cjcm.spring_boot_cero_a_experto.product.application.command.delete.DeleteProductRequest;
import com.cjcm.spring_boot_cero_a_experto.product.application.command.update.UpdateProductRequest;
import com.cjcm.spring_boot_cero_a_experto.product.application.query.getAll.GetAllProductsRequest;
import com.cjcm.spring_boot_cero_a_experto.product.application.query.getAll.GetAllProductsResponse;
import com.cjcm.spring_boot_cero_a_experto.product.application.query.getById.GetProductByIdRequest;
import com.cjcm.spring_boot_cero_a_experto.product.application.query.getById.GetProductByIdResponse;
import com.cjcm.spring_boot_cero_a_experto.product.infrastructure.api.dto.CreateProductDto;
import com.cjcm.spring_boot_cero_a_experto.product.infrastructure.api.dto.ProductDto;
import com.cjcm.spring_boot_cero_a_experto.product.infrastructure.api.dto.UpdateProductDto;
import com.cjcm.spring_boot_cero_a_experto.product.infrastructure.api.mapper.ProductMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController implements ProductApi {

    private final Mediator mediator;

    private final ProductMapper productMapper;

    @GetMapping("")
    public ResponseEntity<List<ProductDto>> getAllProduct(@RequestParam(required = false) String pageSize){

        log.info("Getting all products");

        GetAllProductsResponse response = mediator.dispatch(new GetAllProductsRequest());

        List<ProductDto> productDtos = response.getProducts().stream().map(productMapper::mapToProductDto).toList();

        log.info("Found {} products", productDtos.size());

        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id){

        log.info("Getting product by id");

        GetProductByIdResponse response = mediator.dispatch(new GetProductByIdRequest(id));

        ProductDto productDto = productMapper.mapToProductDto(response.getProduct());

        log.info("Found product with id {}", id);

        return ResponseEntity.ok(productDto);
    }

    @PostMapping("")
    public ResponseEntity<Void> saveProduct(@ModelAttribute @Valid CreateProductDto productDto){

        log.info("Saving product with id {}", productDto.getId());

        CreateProductRequest request = productMapper.mapToCreateProductRequest(productDto);

        mediator.dispatch(request);

        log.info("Saved product with id {}", productDto.getId());

        return ResponseEntity.created(URI.create("/api/v1/products/".concat(productDto.getId().toString()))).build();
    }

    @PutMapping("")
    public ResponseEntity<Void> updateProduct(@ModelAttribute @Valid UpdateProductDto productDto){

        log.info("Updating product with id {}", productDto.getId());

        UpdateProductRequest request = productMapper.mapToUpdateProductRequest(productDto);

        mediator.dispatch(request);

        log.info("Updated product with id {}", productDto.getId());

        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "products", key = "#id")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){

        log.info("Deleting product with id {}", id);

        mediator.dispatchAsync(new DeleteProductRequest(id));

        log.info("Deleted product with id {}", id);

        return ResponseEntity.noContent().build();
    }
}
