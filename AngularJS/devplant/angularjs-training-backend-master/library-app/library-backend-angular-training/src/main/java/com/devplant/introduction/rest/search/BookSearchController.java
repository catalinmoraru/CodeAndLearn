package com.devplant.introduction.rest.search;

import com.devplant.introduction.domain.Book;
import com.devplant.introduction.repository.search.BookSearchRepository;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.InternalAggregations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;

@RestController
@RequestMapping("/api/books")
public class BookSearchController {

	@Autowired
	private BookSearchRepository bookSearchRepository;

	/**
	 * Search for Books using free-text search
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/search")
	public Page<Book> searchBooks(@RequestParam(required = false, defaultValue = "", value = "query") String query,
			@RequestParam(required = false, value = "page", defaultValue = "0") Integer page,
			@RequestParam(required = false, value = "pageSize",defaultValue = "10") Integer pageSize) {

		QueryBuilder queryBuilder = StringUtils.isEmpty(query) ?
				QueryBuilders.queryStringQuery("*") :
				QueryBuilders.matchPhrasePrefixQuery("_all", query);

		return fixEmptyPage(bookSearchRepository.search(queryBuilder, new PageRequest(page, pageSize)));
	}

	private <T> Page<T> fixEmptyPage(Page<T> page) {
		AggregatedPageImpl<T> aggregatedPage = (AggregatedPageImpl<T>) page;
		Aggregations aggregations = aggregatedPage.getAggregations();
		if (aggregations == null) {
			Field field = ReflectionUtils.findField(AggregatedPageImpl.class, "aggregations");
			ReflectionUtils.makeAccessible(field);
			ReflectionUtils.setField(field, aggregatedPage, InternalAggregations.EMPTY);
			return aggregatedPage;
		}
		return page;
	}
}
