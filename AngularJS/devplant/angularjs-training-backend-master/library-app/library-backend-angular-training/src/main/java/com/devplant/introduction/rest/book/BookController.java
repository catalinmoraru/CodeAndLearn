package com.devplant.introduction.rest.book;

import com.devplant.introduction.domain.Author;
import com.devplant.introduction.domain.Book;
import com.devplant.introduction.exception.ObjectCannotBeDeletedException;
import com.devplant.introduction.exception.ObjectDoesNotExistException;
import com.devplant.introduction.repository.jpa.AuthorRepository;
import com.devplant.introduction.repository.jpa.BookRepository;
import com.devplant.introduction.rest.book.model.BookModel;
import org.apache.commons.lang.mutable.MutableBoolean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private AuthorRepository authorRepository;

	/**
	 * Get All Books using pagination and an optional author name
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Page<Book> getBooks(@RequestParam(required = false, value = "authorName") String authorName,
			@RequestParam(required = false, value = "page") Integer page,
			@RequestParam(required = false, value = "pageSize") Integer pageSize) {

		if (page == null) {
			page = 0;
		}
		if (pageSize == null) {
			pageSize = 10;
		}

		if (!StringUtils.isEmpty(authorName)) {
			return bookRepository.findByAuthorName(authorName, new PageRequest(page, pageSize));
		} else {
			return bookRepository.findAll(new PageRequest(page, pageSize));
		}
	}

	@RequestMapping(value = "/{bookId}", method = RequestMethod.GET)
	public Book getBook(@PathVariable("bookId") long bookId) {
		Book book = bookRepository.findOne(bookId);
		if (book == null) {
			throw new ObjectDoesNotExistException("Book with id : " + bookId + " does not exist");
		}

		return book;
	}

	/**
	 * Update an existing book
	 */
	@Transactional
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/{bookId}", method = RequestMethod.POST)
	public Book updateBook(@PathVariable("bookId") long bookId, @RequestBody BookModel bookModel) {

		Book book = bookRepository.findOne(bookId);
		if (book == null) {
			throw new ObjectDoesNotExistException("Book with id : " + bookId + " does not exist");
		}

		return saveBook(bookModel, book);

	}

	/**
	 * Create new book
	 */
	@Transactional
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public Book createBook(@RequestBody BookModel bookModel) {

		Book book = new Book();

		return saveBook(bookModel, book);
	}

	private Book saveBook(BookModel bookModel, Book book) {
		Author author = authorRepository.findOne(bookModel.getAuthor().getId());
		if (author == null) {
			throw new ObjectDoesNotExistException(
					"Author with id : " + bookModel.getAuthor().getId() + " does not exist");
		}

		book.setName(bookModel.getName());
		book.setSynopsis(bookModel.getSynopsis());
		book.setYear(bookModel.getYear());
		book.setIsbn(bookModel.getIsbn());
		book.setAuthor(author);

		return bookRepository.save(book);
	}

	/**
	 * Delete an existing book
	 */
	@Transactional
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/{bookId}", method = RequestMethod.DELETE)
	public void deleteBook(@PathVariable("bookId") long bookId) {

		Book book = bookRepository.findOne(bookId);

		if (book == null) {
			return;
		}
		MutableBoolean canDelete = new MutableBoolean(true);
		book.getStocks().forEach(stock -> {
			if (stock.getUserId() != null) {
				canDelete.setValue(false);
			}
		});

		if (canDelete.isTrue()) {
			bookRepository.delete(book);
		} else {

			throw new ObjectCannotBeDeletedException(
					"Book has reservations, it cannot be deleted until these are returned");
		}

	}

}
