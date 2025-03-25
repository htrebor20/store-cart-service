package com.store.cart_service.application.usecase;

import com.store.cart_service.domain.model.Cart;
import com.store.cart_service.domain.model.OrderStatus;
import com.store.cart_service.domain.ports.in.ICartServicePort;
import com.store.cart_service.domain.ports.out.ICartPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CartUseCase implements ICartServicePort {
    private final ICartPersistencePort cartPersistencePort;

    @Override
    public Cart buyProduct(Cart cart) {
        cart.setOrderStatus(OrderStatus.PENDING);
        return cartPersistencePort.buyProduct(cart);
    }
}
