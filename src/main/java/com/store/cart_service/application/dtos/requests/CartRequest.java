package com.store.cart_service.application.dtos.requests;

import com.store.cart_service.domain.model.ProductItem;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CartRequest {
    private String email;
    private List<ProductItem> products;
}