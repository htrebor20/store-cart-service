package com.store.cart_service.infrastruture.repositories;

import com.store.cart_service.infrastruture.entities.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICartRepository extends JpaRepository<CartEntity, Long> {
}
