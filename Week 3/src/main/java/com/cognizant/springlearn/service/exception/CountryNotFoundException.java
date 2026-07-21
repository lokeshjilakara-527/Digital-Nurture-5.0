package com.cognizant.springlearn.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when a country code is not found (Doc 2 - exceptional scenario).
 * @ResponseStatus makes Spring return HTTP 404 with the given reason.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Country not found")
public class CountryNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public CountryNotFoundException() {
        super("Country not found");
    }
}
