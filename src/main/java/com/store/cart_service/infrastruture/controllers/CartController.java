package com.store.cart_service.infrastruture.controllers;

import com.store.cart_service.application.dtos.requests.CartRequest;
import com.store.cart_service.application.dtos.responses.CartResponseDto;
import com.store.cart_service.application.mappers.ICartRequestMappers;
import com.store.cart_service.domain.model.Cart;
import com.store.cart_service.domain.ports.in.ICartServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final ICartServicePort cartServicePort;
    private final ICartRequestMappers cartRequestMappers;

    @PostMapping()

    public ResponseEntity<CartResponseDto> addProduct(@RequestBody CartRequest cartRequest) {
        Cart model = cartRequestMappers.toModel(cartRequest);
        Cart cart = cartServicePort.buyProduct(model);

        CartResponseDto response = cartRequestMappers.toResponse(cart);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CartResponseDto> getCart(@PathVariable Long id) {
        CartResponseDto response = cartRequestMappers.toResponse(cartServicePort.getCart(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
