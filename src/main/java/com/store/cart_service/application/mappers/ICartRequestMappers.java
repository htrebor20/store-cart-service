package com.store.cart_service.application.mappers;

import com.store.cart_service.application.dtos.requests.CartRequest;
import com.store.cart_service.application.dtos.responses.CartResponseDto;
import com.store.cart_service.domain.model.Cart;
import com.store.cart_service.domain.model.ProductItem;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICartRequestMappers {


    Cart toModel(CartRequest cartRequest);


    CartResponseDto toResponse(Cart cart);

    @Named("mapProductItems")
    default List<ProductItem> mapProductItems(List<ProductItem> products) {
        return products != null ? products : Collections.emptyList();
    }
}