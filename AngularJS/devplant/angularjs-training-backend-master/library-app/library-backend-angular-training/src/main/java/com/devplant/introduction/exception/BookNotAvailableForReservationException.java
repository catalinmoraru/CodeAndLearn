package com.devplant.introduction.exception;

public class BookNotAvailableForReservationException extends RuntimeException {

	public BookNotAvailableForReservationException(){
		super("Book is not available for reservation");
	}
}
