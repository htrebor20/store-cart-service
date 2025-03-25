package com.store.cart_service.infrastruture.mappers;

import com.store.cart_service.domain.model.Cart;
import com.store.cart_service.domain.model.ProductItem;
import com.store.cart_service.infrastruture.entities.CartEntity;
import com.store.cart_service.infrastruture.entities.CartProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICartEntityMapper {

    @Mapping(target = "cartProducts", source = "products", qualifiedByName = "mapProducts")
    CartEntity toEntity(Cart cart);

    @Mapping(target = "products", source = "cartProducts", qualifiedByName = "mapProductItems")
    Cart toModel(CartEntity cartEntity);

    @Named("mapProducts")
    default List<CartProductEntity> mapProducts(List<ProductItem> products) {
        if (products == null) {
            return Collections.emptyList();
        }
        return products.stream()
                .map(productItem -> new CartProductEntity(null, null, productItem.getProductId(), productItem.getQuantity()))
                .collect(Collectors.toList());
    }

    @Named("mapProductItems")
    default List<ProductItem> mapProductItems(List<CartProductEntity> cartProducts) {
        if (cartProducts == null) {
            return Collections.emptyList();
        }
        return cartProducts.stream()
                .map(cartProduct -> new ProductItem(cartProduct.getProductId(), cartProduct.getQuantity()))
                .collect(Collectors.toList());
    }
}
