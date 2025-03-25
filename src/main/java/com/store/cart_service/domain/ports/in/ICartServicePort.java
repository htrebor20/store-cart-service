package com.store.cart_service.domain.ports.in;

import com.store.cart_service.domain.model.Cart;

public interface ICartServicePort {
    Cart buyProduct(Cart cart);
}
