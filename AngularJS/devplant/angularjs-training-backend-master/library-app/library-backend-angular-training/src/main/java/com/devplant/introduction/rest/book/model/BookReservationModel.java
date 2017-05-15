package com.devplant.introduction.rest.book.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BookReservationModel {

	@NotNull
	private long pickupTimestamp;
	@NotNull
	private Long bookId;
}
