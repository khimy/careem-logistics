package com.ca.order.exception;

/**
 * @author vivek.
 * If the notification request is empty
 */
public class InvalidOrderRequest extends RuntimeException{

    public InvalidOrderRequest(String message) {
        super(message);
    }

}
