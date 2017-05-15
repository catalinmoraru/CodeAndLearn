package com.devplant.introduction.exception;

public class BookAlreadyReservedByUserException extends RuntimeException {

	public BookAlreadyReservedByUserException() {
		super("You have already reserved this book");
	}
}
