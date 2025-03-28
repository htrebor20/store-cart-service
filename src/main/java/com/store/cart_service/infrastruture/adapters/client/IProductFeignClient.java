package com.store.cart_service.infrastruture.adapters.client;

import com.store.cart_service.domain.model.ProductRequest;
import com.store.cart_service.domain.model.ProductStockResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "product-service", url = "http://localhost:8088", configuration = FeignClientProperties.FeignClientConfiguration.class)
public interface IProductFeignClient {
    @GetMapping("/products/validate-stock")
    ResponseEntity<List<ProductStockResponse>> validateStock(@RequestParam List<Long> productIds);

    @PostMapping("/products/update-list")
    ResponseEntity<Void> updateProducts(@RequestBody List<ProductRequest> productRequests);
}