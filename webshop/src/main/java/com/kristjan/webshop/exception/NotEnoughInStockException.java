package com.kristjan.webshop.exception;

public class NotEnoughInStockException extends Exception {
    public NotEnoughInStockException(String message) {
        super(message);
    }
}