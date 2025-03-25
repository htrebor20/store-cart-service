package com.store.cart_service.infrastruture.adapters;

import com.store.cart_service.domain.model.Cart;
import com.store.cart_service.domain.ports.out.ICartPersistencePort;
import com.store.cart_service.infrastruture.entities.CartEntity;
import com.store.cart_service.infrastruture.mappers.ICartEntityMapper;
import com.store.cart_service.infrastruture.repositories.ICartRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CartPersistenceAdapter implements ICartPersistencePort {
    private final ICartRepository cartRepository;
    private final ICartEntityMapper cartEntityMapper;

    @Override
    public Cart buyProduct(Cart cart) {
        CartEntity cartEntity = cartEntityMapper.toEntity(cart);
        cartEntity.getCartProducts().forEach(cartProduct -> cartProduct.setCart(cartEntity));
        CartEntity save = cartRepository.save(cartEntity);
        return cartEntityMapper.toModel(save);
    }
}
