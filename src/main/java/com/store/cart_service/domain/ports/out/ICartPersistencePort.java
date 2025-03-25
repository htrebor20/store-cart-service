package com.store.cart_service.domain.ports.out;

import com.store.cart_service.domain.model.Cart;

public interface ICartPersistencePort {
    Cart buyProduct(Cart cart);
}
