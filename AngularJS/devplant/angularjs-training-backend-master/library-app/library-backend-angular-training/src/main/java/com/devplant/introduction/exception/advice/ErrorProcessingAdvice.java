package com.devplant.introduction.exception.advice;

import com.devplant.introduction.exception.*;
import com.devplant.introduction.exception.model.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ErrorProcessingAdvice {

	@ResponseBody
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	@ExceptionHandler(value = { AuthenticationCredentialsNotFoundException.class, AccessDeniedException.class })
	public ErrorModel handleAuthorizationException(Exception exception) {
		return new ErrorModel(exception);
	}

	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = { ObjectDoesNotExistException.class })
	public ErrorModel handleObjectDoesNotExistException(ObjectDoesNotExistException exception) {
		return new ErrorModel(exception);
	}

	@ResponseBody
	@ResponseStatus(value = HttpStatus.CONFLICT)
	@ExceptionHandler(value = { ObjectAlreadyExistsException.class })
	public ErrorModel hanldeObjectAlreadyExistsException(ObjectAlreadyExistsException exception) {
		return new ErrorModel(exception);
	}

	@ResponseBody
	@ResponseStatus(value = HttpStatus.CONFLICT)
	@ExceptionHandler(value = { ObjectCannotBeDeletedException.class })
	protected ErrorModel handleObjectCannotBeDeletedException(ObjectCannotBeDeletedException exception) {
		return new ErrorModel(exception);
	}

	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = { Exception.class })
	public ErrorModel handleDefault(Exception exception) {
		return new ErrorModel(exception);
	}
}
