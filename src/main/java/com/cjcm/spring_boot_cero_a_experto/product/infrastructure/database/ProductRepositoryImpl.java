package com.cjcm.spring_boot_cero_a_experto.product.infrastructure.database;

import com.cjcm.spring_boot_cero_a_experto.product.domain.entity.Product;
import com.cjcm.spring_boot_cero_a_experto.product.domain.port.ProductRepository;
import com.cjcm.spring_boot_cero_a_experto.product.infrastructure.database.entity.ProductEntity;
import com.cjcm.spring_boot_cero_a_experto.product.infrastructure.database.mapper.ProductEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ProductRepositoryImpl implements ProductRepository {

    private final List<ProductEntity> products = new ArrayList<>();

    private final ProductEntityMapper productEntityMapper;

    @Override
    public void upsert(Product product) {
        ProductEntity productEntity = productEntityMapper.maptoProductEntity(product);
        products.removeIf(p -> p.getId().equals(productEntity.getId()));
        products.add(productEntity);
    }

    @Cacheable(value = "products", key = "#id")
    @Override
    public Optional<Product> findById(Long id) {

        log.info("Finding product with id on infrastructure/Database{}", id);

        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .map(productEntityMapper::maptoProduct);
    }

    @Override
    public List<Product> findAll() {
        return products.stream()
                .map(productEntityMapper::maptoProduct)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        products.removeIf(p -> p.getId().equals(id));
    }
}
