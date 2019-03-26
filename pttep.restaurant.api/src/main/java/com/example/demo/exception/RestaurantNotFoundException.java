package com.example.demo.exception;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.UUID;

public class RestaurantNotFoundException extends RuntimeException {

	public static final long serialVersionUID = 1L;

    public RestaurantNotFoundException(String id) {
		super("Could not find Restaurant " + id);
	}
}