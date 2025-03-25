package com.store.cart_service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Cart {
    private Long orderId;
    private String email;
    private List<ProductItem> products;
    private OrderStatus orderStatus;

}
