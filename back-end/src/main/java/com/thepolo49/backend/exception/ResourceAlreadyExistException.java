package com.thepolo49.backend.exception;

public class ResourceAlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceAlreadyExistException(String message) {
        super(message);
    }

}
