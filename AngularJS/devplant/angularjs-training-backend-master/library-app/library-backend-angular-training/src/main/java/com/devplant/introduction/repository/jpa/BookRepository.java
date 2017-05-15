package com.devplant.introduction.repository.jpa;

import com.devplant.introduction.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

	Page<Book> findByAuthorName(@Param("authorName") String authorName, Pageable pageable);

	@Query("SELECT DISTINCT b FROM Book b JOIN b.stocks s WHERE s.user IS NULL")
	List<Book> findByAvailableStocks();


}
