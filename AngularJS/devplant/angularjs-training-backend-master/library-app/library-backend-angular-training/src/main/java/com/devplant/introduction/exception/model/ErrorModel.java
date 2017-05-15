package com.devplant.introduction.exception.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorModel {
	private String message;

	public ErrorModel(Exception e) {
		this.message = e.getMessage();
	}
}
