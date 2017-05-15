package com.devplant.introduction.exception;

public class ObjectCannotBeDeletedException extends  RuntimeException {

	public ObjectCannotBeDeletedException(String message){
		super(message);
	}
}
