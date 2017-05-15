package com.devplant.introduction.rest.user;

import com.devplant.introduction.exception.UserAlreadyExistsException;
import com.devplant.introduction.exception.model.ErrorModel;
import com.devplant.introduction.rest.user.model.UserRegistrationModel;
import com.devplant.introduction.security.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-management")
public class RegistrationController {

	@Autowired
	private UserService userService;

	/**
	 * Register a new account for the DevPlant Library app
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<Void> register(@RequestBody UserRegistrationModel userRegistrationModel) {
		userService.register(userRegistrationModel);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ResponseBody
	@ResponseStatus(value = HttpStatus.CONFLICT)
	@ExceptionHandler(value = { UserAlreadyExistsException.class })
	protected ErrorModel handleUserAlreadyExistsException(UserAlreadyExistsException exception)
			throws JsonProcessingException {
		return new ErrorModel(exception);
	}

}
