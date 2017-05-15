package com.devplant.introduction.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(of = "id")
@ToString(exclude = "authoredBooks")
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(unique = true)
	private String name;

	@JsonIgnore
	@OneToMany(mappedBy = "author")
	private Set<Book> authoredBooks;

	public boolean hasBooks(){
		return (getAuthoredBooks()!=null && getAuthoredBooks().size() > 0);
	}
}
