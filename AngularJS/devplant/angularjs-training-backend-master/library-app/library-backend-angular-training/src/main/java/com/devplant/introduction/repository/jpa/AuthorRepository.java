package com.devplant.introduction.repository.jpa;

import com.devplant.introduction.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface AuthorRepository extends JpaRepository<Author, Long> {

	Author findOneByNameIgnoreCase(@Param("name") String name);

}