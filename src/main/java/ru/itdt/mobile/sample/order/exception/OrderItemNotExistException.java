package ru.itdt.mobile.sample.order.exception;

public class OrderItemNotExistException extends RuntimeException {
    public OrderItemNotExistException(String message) {
        super(message);
    }
}
