package com.cjcm.spring_boot_cero_a_experto.product.infrastructure.database.mapper;

import com.cjcm.spring_boot_cero_a_experto.product.domain.entity.Product;
import com.cjcm.spring_boot_cero_a_experto.product.infrastructure.database.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProductEntityMapper {
    ProductEntity maptoProductEntity(Product product);

    Product maptoProduct(ProductEntity productEntity);
}
