package com.devplant.introduction.exception;

public class ObjectAlreadyExistsException extends RuntimeException {
	public ObjectAlreadyExistsException(String message) {
		super(message);
	}
}
