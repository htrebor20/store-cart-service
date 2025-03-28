package com.store.cart_service.domain.model;

import lombok.Getter;

@Getter
public class ProductStockResponse {
    private Long productId;
    private Integer stock;
}
