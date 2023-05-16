package com.example.springbasics.services.area;

public class AreaNotFoundException extends Exception {
    public AreaNotFoundException(Throwable cause) {
        super(cause);
    }

    public AreaNotFoundException(String message) {
        super(message);
    }

}
