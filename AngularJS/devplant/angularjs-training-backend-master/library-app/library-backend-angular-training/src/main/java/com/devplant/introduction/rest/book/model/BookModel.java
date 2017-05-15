package com.devplant.introduction.rest.book.model;

import lombok.Data;

@Data
public class BookModel {

	private int year;
	private String name;
	private String synopsis;
	private String isbn;

	private BookAuthorModel author;

}
