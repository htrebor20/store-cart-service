package com.store.cart_service.application.usecase;

import com.store.cart_service.application.constants.ErrorMessages;
import com.store.cart_service.domain.exception.BadRequestValidationException;
import com.store.cart_service.domain.exception.ExternalServiceException;
import com.store.cart_service.domain.exception.ServiceUnavailableException;
import com.store.cart_service.domain.model.*;
import com.store.cart_service.domain.ports.in.ICartServicePort;
import com.store.cart_service.domain.ports.out.ICartPersistencePort;
import com.store.cart_service.infrastruture.adapters.client.IProductFeignClient;
import com.store.cart_service.infrastruture.adapters.client.IUserFeignClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CartUseCase implements ICartServicePort {
    private final ICartPersistencePort cartPersistencePort;
    private final IUserFeignClient userFeignClient;
    private final IProductFeignClient productFeignClient;

    @Override
    public Cart buyProduct(Cart cart) {
        try {
            validateUser(cart);
            validateProduct(cart);
            updateInventory(cart);
            cart.setOrderStatus(OrderStatus.PENDING);
            return cartPersistencePort.buyProduct(cart);

        } catch (FeignException e) {
            handleFeignException(e, ErrorMessages.EXTERNAL_SERVICE_ERROR);
            return null;
        }
    }

    @Override
    public Cart getCart(Long id) {
        return cartPersistencePort.getCart(id);
    }

    private void validateUser(Cart cart) {
        try {
            ResponseEntity<User> userClient = userFeignClient.findUserById(cart.getEmail());

            if (!userClient.getStatusCode().is2xxSuccessful() || !userClient.hasBody()) {
                throw new BadRequestValidationException(ErrorMessages.getEmailNotFoundMessage(cart.getEmail()));
            }
        } catch (FeignException e) {
            handleFeignException(e, ErrorMessages.EXTERNAL_SERVICE_ERROR);
        }
    }

    private void validateProduct(Cart cart) {
        List<Long> productIds = cart.getProducts()
                .stream()
                .map(ProductItem::getProductId)
                .collect(Collectors.toList());

        try {
            ResponseEntity<List<ProductStockResponse>> responseEntity = productFeignClient.validateStock(productIds);

            if (!responseEntity.getStatusCode().is2xxSuccessful() || responseEntity.getBody() == null) {
                throw new ExternalServiceException(ErrorMessages.EXTERNAL_SERVICE_ERROR);
            }

            Map<Long, Integer> stockMap = responseEntity.getBody()
                    .stream()
                    .collect(Collectors.toMap(ProductStockResponse::getProductId, ProductStockResponse::getStock));

            boolean allProductsAvailable = cart.getProducts().stream().allMatch(productItem -> {
                Integer stock = stockMap.get(productItem.getProductId());
                return stock != null && stock >= productItem.getQuantity();
            });

            if (!allProductsAvailable) {
                throw new BadRequestValidationException(ErrorMessages.NOT_ENOUGH_STOCK);
            }
        } catch (FeignException e) {
            handleFeignException(e, ErrorMessages.EXTERNAL_SERVICE_ERROR);
        }
    }

    private void updateInventory(Cart cart) {
        List<ProductRequest> productRequests = cart.getProducts().stream()
                .map(productItem -> new ProductRequest(productItem.getProductId(), productItem.getQuantity(), true))
                .toList();

        try {
            ResponseEntity<Void> response = productFeignClient.updateProducts(productRequests);

            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new ExternalServiceException(ErrorMessages.ERROR_UPDATING_STOCK);
            }
        } catch (FeignException e) {
            handleFeignException(e, ErrorMessages.ERROR_UPDATING_STOCK);
        }
    }

    private void handleFeignException(FeignException e, String defaultMessage) {
        if (e instanceof FeignException.NotFound) {
            throw new BadRequestValidationException(ErrorMessages.RESOURCE_NOT_FOUND);
        } else if (e instanceof FeignException.ServiceUnavailable) {
            throw new ServiceUnavailableException(ErrorMessages.SERVICE_UNAVAILABLE);
        } else {
            throw new ExternalServiceException(defaultMessage + ": " + e.getMessage());
        }
    }
}
