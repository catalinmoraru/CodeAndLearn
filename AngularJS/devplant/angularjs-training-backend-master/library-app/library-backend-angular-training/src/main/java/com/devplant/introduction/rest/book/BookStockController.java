package com.devplant.introduction.rest.book;

import com.devplant.introduction.domain.Book;
import com.devplant.introduction.domain.BookStock;
import com.devplant.introduction.exception.ObjectDoesNotExistException;
import com.devplant.introduction.repository.jpa.BookRepository;
import com.devplant.introduction.repository.jpa.BookStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@RestController
public class BookStockController {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private BookStockRepository bookStockRepository;

	/**
	 * Get stocks ( number of copies available ) for a specific book
	 */
	@Transactional
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/book-stocks/{bookId}", method = RequestMethod.GET)
	public List<BookStock> getStocksForBook(@PathVariable("bookId") long bookId) {

		Book book = bookRepository.findOne(bookId);
		if (book == null) {
			throw new ObjectDoesNotExistException("Book with id : " + bookId + " does not exist");
		}

		return book.getStocks();

	}

	/**
	 * Remove a given book stock
	 */
	@Transactional
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/api/book-stocks/{bookId}", method = RequestMethod.DELETE)
	public void removeBookStock(@PathVariable("bookId") long bookId) {

		Book book = bookRepository.findOne(bookId);
		if (book == null) {
			throw new ObjectDoesNotExistException("Book with id : " + bookId + " does not exist");
		}

		BookStock bookStock = book.getStocks().stream().filter(stock -> stock.getUserId() != null).findAny()
				.orElse(null);

		if (bookStock != null) {
			bookStockRepository.delete(bookStock);
		}
	}

	/**
	 * Add Stocks to a given book
	 */
	@Transactional
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/api/book-stocks/add/{bookId}", method = RequestMethod.POST)
	public List<BookStock> addBookStockToBook(@PathVariable("bookId") long bookId,
			@RequestParam(value = "count", required = false, defaultValue = "1") int count) {

		Book book = bookRepository.findOne(bookId);
		if (book == null) {
			throw new ObjectDoesNotExistException("Book with id : " + bookId + " does not exist");
		}
		List<BookStock> bookStocks = new ArrayList<>();
		IntStream.rangeClosed(1, count).forEach(i -> {
			BookStock bookStock = new BookStock();
			bookStock.setBook(book);
			bookStocks.add(bookStock);
		});

		return bookStockRepository.save(bookStocks);
	}

	/**
	 * Mark a book-stock as being picked up by the user
	 */
	@Transactional
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/api/book-stocks/picked-up/{bookStockId}", method = RequestMethod.POST)
	public BookStock bookPickedUp(@PathVariable("bookStockId") long bookStockId) {

		BookStock bookStock = bookStockRepository.findOne(bookStockId);
		bookStock.setPickedUp(true);
		bookStockRepository.save(bookStock);

		return bookStock;
	}

	/**
	 * Mark a book-stock as returned by the user
	 */
	@Transactional
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/api/book-stocks/returned/{bookStockId}", method = RequestMethod.POST)
	public BookStock bookReturned(@PathVariable("bookStockId") long bookStockId) {

		BookStock bookStock = bookStockRepository.findOne(bookStockId);
		bookStock.setPickedUp(false);
		bookStock.setUser(null);
		bookStock.setReservationTimestamp(null);
		bookStock.setPickupTimestamp(null);
		bookStock.setReservationTimestamp(null);
		bookStockRepository.save(bookStock);

		return bookStock;

	}

}
