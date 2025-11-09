package com.example.waycare.exceptions;

public class GoogleMapsApiException extends RuntimeException {

    public GoogleMapsApiException(String message) {
        super(message);
    }

    public GoogleMapsApiException(String message, Throwable cause) {
        super(message, cause);
    }
}