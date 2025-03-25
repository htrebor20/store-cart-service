package com.store.cart_service.domain.model;

public class Cart {
    private Long orderId;
    private String email;
    private Long idProduct;
    private Integer quantity;

    public Cart(Long orderId, String email, Long idProduct, Integer quantity) {
        this.orderId = orderId;
        this.email = email;
        this.idProduct = idProduct;
        this.quantity = quantity;
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getEmail() {
        return email;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
