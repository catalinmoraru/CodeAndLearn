package com.devplant.introduction.rest.user.model;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

@Data
public class UserRegistrationModel {

	@Email
	private String email;

	@NotEmpty
	@Size(min = 6)
	private String password;

	@NotEmpty
	private String firstName;

	@NotEmpty
	private String lastName;

}
