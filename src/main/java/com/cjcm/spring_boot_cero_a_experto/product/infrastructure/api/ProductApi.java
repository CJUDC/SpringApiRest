package com.cjcm.spring_boot_cero_a_experto.product.infrastructure;

import com.cjcm.spring_boot_cero_a_experto.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ProductApi {
    ResponseEntity<List<Product>> getAllProduct(@RequestParam(required = false) String pageSize);

    ResponseEntity<Product> getProductById(@PathVariable Long id);

    ResponseEntity<Void> saveProduct(@RequestBody Product product);

    ResponseEntity<Void> deleteProduct(@PathVariable Long id);

}
