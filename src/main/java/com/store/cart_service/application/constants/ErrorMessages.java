package com.store.cart_service.application.constants;

public class ErrorMessages {
    private ErrorMessages() {

    }


    public static final String EMAIL_NOT_FOUND = "The email %s is not registered.";
    public static final String SERVICE_UNAVAILABLE = "User service is currently unavailable. Please try again later.";
    public static final String EXTERNAL_SERVICE_ERROR = "An error occurred while communicating with the user service: ";
    public static final String NOT_ENOUGH_STOCK = "Not enough stock for one or more products.";
    public static final String ERROR_UPDATING_STOCK = "Error updating product stock.";
    public static final String RESOURCE_NOT_FOUND = "The requested resource was not found.";
    public static final String CART_NOT_FOUND = "Cart with ID %d was not found.";

    public static String getEmailNotFoundMessage(String email) {
        return String.format(EMAIL_NOT_FOUND, email);
    }

    public static String getCartNotFoundMessage(Long id) {
        return String.format(CART_NOT_FOUND, id);
    }
}