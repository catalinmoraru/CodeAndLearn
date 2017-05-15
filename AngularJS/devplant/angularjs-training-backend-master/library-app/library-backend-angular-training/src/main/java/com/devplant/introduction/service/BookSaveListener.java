package com.devplant.introduction.service;

import com.devplant.introduction.domain.Book;
import com.devplant.introduction.repository.search.BookSearchRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class BookSaveListener {

	@Autowired
	private BookSearchRepository bookSearchRepository;

	@Pointcut("execution(* com.devplant.introduction.repository.jpa.BookRepository.save(..))")
	public void save() {
		// PointCut
	}

	@Pointcut("execution(* com.devplant.introduction.repository.jpa.BookRepository.saveAndFlush(..))")
	public void saveAndFlush() {
		// PointCut
	}

	@Pointcut("execution(* com.devplant.introduction.repository.jpa.BookRepository.deleteAllInBatch(..))")
	public void deleteAllInBatch() {
		// PointCut
	}

	@Pointcut("execution(* com.devplant.introduction.repository.jpa.BookRepository.deleteInBatch(..))")
	public void deleteInBatch() {
		// PointCut
	}

	@Pointcut("execution(* com.devplant.introduction.repository.jpa.BookRepository.deleteAll(..))")
	public void deleteAll() {
		// PointCut
	}

	@Pointcut("execution(* com.devplant.introduction.repository.jpa.BookRepository.delete(..))")
	public void delete() {
		// PointCut
	}

	@Around("deleteInBatch() || deleteAll() || delete() || deleteAllInBatch()")
	public Object handleDelete(ProceedingJoinPoint pjp) throws Throwable {
		Object output = pjp.proceed();
		for (Object argument : pjp.getArgs()) {
			if (argument instanceof Book) {
				bookSearchRepository.delete((Book) argument);
			}
			if (argument instanceof Iterable) {
				for (Object element : (Iterable) argument) {
					if (element instanceof Book) {
						bookSearchRepository.delete((Book) element);
					}
				}
			}
		}
		return output;
	}

	@Around("save()' || saveAndFlush() ")
	public Object handleSave(ProceedingJoinPoint pjp) throws Throwable {
		Object output = pjp.proceed();
		for (Object argument : pjp.getArgs()) {
			if (argument instanceof Book) {
				bookSearchRepository.save((Book) argument);
			}
			if (argument instanceof Iterable) {
				for (Object element : (Iterable) argument) {
					if (element instanceof Book) {
						bookSearchRepository.save((Book) element);
					}
				}
			}
		}
		return output;
	}

}
