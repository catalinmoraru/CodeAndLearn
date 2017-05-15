package com.devplant.introduction.rest.book.model;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class AuthorModel {

	@Size(min = 1)
	private String name;

}
