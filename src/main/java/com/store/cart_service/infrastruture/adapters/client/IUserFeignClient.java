package com.store.cart_service.infrastruture.adapters.client;

import com.store.cart_service.domain.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service", url = "http://localhost:8080", configuration = FeignClientProperties.FeignClientConfiguration.class)
public interface IUserFeignClient {
    @GetMapping("/user/email")
    ResponseEntity<User> findUserById(@RequestParam String email);
}

