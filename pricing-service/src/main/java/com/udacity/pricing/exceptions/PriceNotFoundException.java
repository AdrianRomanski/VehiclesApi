package com.udacity.pricing.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Price not found")
public class PriceNotFoundException extends RuntimeException {

    public PriceNotFoundException() {
    }

    public PriceNotFoundException(String message) {
        super(message);
    }
}
