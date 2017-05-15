package com.devplant.introduction.rest.book;

import com.devplant.introduction.domain.Book;
import com.devplant.introduction.domain.BookStock;
import com.devplant.introduction.exception.*;
import com.devplant.introduction.exception.model.ErrorModel;
import com.devplant.introduction.repository.jpa.BookRepository;
import com.devplant.introduction.repository.jpa.BookStockRepository;
import com.devplant.introduction.rest.book.model.BookReservationModel;
import com.devplant.introduction.rest.book.model.BooleanResponseModel;
import com.devplant.introduction.service.BookReservationService;
import org.apache.commons.lang.mutable.MutableBoolean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/books/reservations")
public class BookReservationController {

	@Autowired
	private BookReservationService bookReservationService;

	@Autowired
	private BookStockRepository bookStockRepository;

	@Autowired
	private BookRepository bookRepository;

	/**
	 * Make a reservation for a book, you'll need to pick it up :)
	 */
	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public BookStock makeReservation(@RequestBody BookReservationModel bookReservationModel, Principal principal) {

		return bookReservationService
				.reserveBook(bookReservationModel.getPickupTimestamp(), bookReservationModel.getBookId(),
						principal.getName());
	}

	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "/mine", method = RequestMethod.GET)
	public List<BookStock> myReservations(Principal principal) {
		return bookStockRepository.findByUsername(principal.getName());
	}

	/**
	 * Check availability for a book
	 */
	@Transactional
	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "/check-availability/{bookId}", method = RequestMethod.GET)
	public BooleanResponseModel checkAvailability(@PathVariable("bookId") Long bookId) {

		Book book = bookRepository.findOne(bookId);
		if (book == null) {
			throw new ObjectDoesNotExistException("Book with id : " + bookId + " does not exist");
		}

		MutableBoolean exists = new MutableBoolean(false);

		book.getStocks().forEach(s -> {
			if (s.getUserId() == null) {
				exists.setValue(true);
			}
		});

		return new BooleanResponseModel(exists.booleanValue());
	}

	/**
	 * Cancel an existing reservation you made
	 */
	@Transactional
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "/{bookStockId}", method = RequestMethod.DELETE)
	public void cancelReservation(@PathVariable("bookStockId") long bookStockId, Principal principal) {

		BookStock bookStock = bookStockRepository.findOne(bookStockId);
		if (bookStock.getUser().getUsername().equals(principal.getName())) {
			bookStock.setUser(null);
			bookStock.setPickedUp(false);
			bookStock.setPickupTimestamp(null);
			bookStock.setReservationTimestamp(null);
			bookStock.setReturnTimestamp(null);
			bookStockRepository.save(bookStock);
		}

	}

	@ResponseBody
	@ResponseStatus(value = HttpStatus.CONFLICT)
	@ExceptionHandler(
			value = { BookNotAvailableForReservationException.class, BookPickupDateIsToFarInTheFutureException.class,
					BookAlreadyReservedByUserException.class })
	protected ErrorModel handleReservationException(Exception exception) {
		return new ErrorModel(exception);
	}

}
