package com.store.cart_service.infrastruture.confuguration;

import com.store.cart_service.application.usecase.CartUseCase;
import com.store.cart_service.domain.ports.in.ICartServicePort;
import com.store.cart_service.domain.ports.out.ICartPersistencePort;
import com.store.cart_service.infrastruture.adapters.CartPersistenceAdapter;
import com.store.cart_service.infrastruture.adapters.client.IProductFeignClient;
import com.store.cart_service.infrastruture.adapters.client.IUserFeignClient;
import com.store.cart_service.infrastruture.mappers.ICartEntityMapper;
import com.store.cart_service.infrastruture.repositories.ICartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final ICartEntityMapper cartEntityMapper;
    private final ICartRepository cartRepository;
    private final IUserFeignClient userFeignClient;
    private final IProductFeignClient productFeignClient;

    @Bean
    public ICartPersistencePort cartPersistencePort() {
        return new CartPersistenceAdapter(cartRepository, cartEntityMapper);
    }

    @Bean
    public ICartServicePort cartServicePort() {
        return new CartUseCase(cartPersistencePort(), userFeignClient, productFeignClient);
    }
}
