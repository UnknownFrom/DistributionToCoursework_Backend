package ru.itdt.mobile.sample.order.exception;

public class ShopOrderNotExistException extends RuntimeException {
    public ShopOrderNotExistException(String message) {
        super(message);
    }
}
