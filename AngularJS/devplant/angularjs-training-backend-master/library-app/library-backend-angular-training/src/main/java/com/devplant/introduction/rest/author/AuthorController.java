package com.devplant.introduction.rest.author;

import com.devplant.introduction.domain.Author;
import com.devplant.introduction.exception.*;
import com.devplant.introduction.repository.jpa.AuthorRepository;
import com.devplant.introduction.rest.book.model.AuthorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/author")
public class AuthorController {

	@Autowired
	private AuthorRepository authorRepository;

	/**
	 * Get all existing Authors
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Author> getAuthors() {

		return authorRepository.findAll();

	}

	@RequestMapping(value = "/{authorId}", method = RequestMethod.GET)
	public Author getAuthor(@PathVariable("authorId") long authorId) {
		Author author = authorRepository.findOne(authorId);
		if (author == null) {
			throw new ObjectDoesNotExistException("Author with id : " + authorId + " does not exist");
		}

		return author;
	}

	/**
	 * Update a specific author
	 */
	@Transactional
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/{authorId}", method = RequestMethod.POST)
	public Author updateAuthor(@PathVariable("authorId") long authorId, @RequestBody AuthorModel authorModel) {

		Author author = authorRepository.findOne(authorId);
		if (author == null) {
			throw new ObjectDoesNotExistException("Author with id : " + authorId + " does not exist");
		}

		return saveAuthor(authorModel, author);

	}

	/**
	 * Create a new author
	 */
	@Transactional
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public Author createAuthor(@RequestBody AuthorModel authorModel) {

		Author author = new Author();

		return saveAuthor(authorModel, author);
	}

	private Author saveAuthor(AuthorModel authorModel, Author author) {
		Author existingByName = authorRepository.findOneByNameIgnoreCase(authorModel.getName());
		if (existingByName != null) {
			throw new ObjectAlreadyExistsException("Author with name : " + authorModel.getName() + " Already exists");
		}

		author.setName(authorModel.getName());
		return authorRepository.save(author);

	}

	/**
	 * Delete an existing Author
	 */
	@Transactional
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/{authorId}", method = RequestMethod.DELETE)
	public void deleteAuthor(@PathVariable("authorId") long authorId) {

		Author author = authorRepository.findOne(authorId);

		if (author == null) {
			return;
		}
		if (author.getAuthoredBooks().size() > 0) {
			throw new ObjectCannotBeDeletedException("Author is assigned to books, it cannot be deleted!");
		}

		authorRepository.delete(authorId);

	}

}
