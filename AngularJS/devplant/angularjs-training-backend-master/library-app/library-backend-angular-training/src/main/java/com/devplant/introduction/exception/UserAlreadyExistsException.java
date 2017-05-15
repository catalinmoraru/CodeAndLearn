package com.devplant.introduction.exception;

public class UserAlreadyExistsException extends RuntimeException {

	public UserAlreadyExistsException(){
		super("User Already Exists");
	}

}
