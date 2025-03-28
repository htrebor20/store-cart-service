package com.store.cart_service.domain.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private  String name;
    private  String lastName;
    private  Long document;
    private  Long cellphone;
    private  String email;
}
