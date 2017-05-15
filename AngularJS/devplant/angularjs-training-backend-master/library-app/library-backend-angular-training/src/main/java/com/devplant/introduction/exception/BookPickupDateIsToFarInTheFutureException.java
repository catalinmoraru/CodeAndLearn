package com.devplant.introduction.exception;

public class BookPickupDateIsToFarInTheFutureException extends RuntimeException {

	public BookPickupDateIsToFarInTheFutureException(String message){
		super(message);
	}
}
