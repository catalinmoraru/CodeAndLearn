package com.devplant.introduction.domain;

import com.devplant.introduction.service.BookSaveListener;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.apache.commons.lang.mutable.MutableInt;
import org.springframework.data.elasticsearch.annotations.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Document(indexName = "book-search")
@EqualsAndHashCode(of = "id")
@ToString(exclude = { "stocks", "author" })
@EntityListeners(BookSaveListener.class)
public class Book {

	@Id
	@org.springframework.data.annotation.Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private int year;
	private String name;

	@Column(length = 4000)
	private String synopsis;

	private String isbn;

	@JsonIgnore
	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
	@Field(type = FieldType.Object, ignoreFields = { "book", "user" })
	private List<BookStock> stocks;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH },
			fetch = FetchType.EAGER)
	@Field(type = FieldType.Object, ignoreFields = { "authoredBooks" })
	private Author author;

	public int getNumberOfCopies() {
		return this.getStocks() == null ? 0 : this.getStocks().size();
	}

	public int getNumberOfRentedCopies() {
		if(this.getStocks() == null){
			return 0;
		}
		MutableInt count = new MutableInt(0);
		getStocks().forEach(stock -> {
			if (stock.getUserId() != null) {
				count.setValue(count.intValue() + 1);
			}
		});

		return count.intValue();
	}
}
