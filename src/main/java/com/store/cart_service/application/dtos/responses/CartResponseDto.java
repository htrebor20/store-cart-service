package com.store.cart_service.application.dtos.responses;

import com.store.cart_service.domain.model.ProductItem;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CartResponseDto {
    private Long orderId;
    private String email;
    private List<ProductItem> products;
}