package com.javastack.spring.authentication.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.javastack.spring.authentication.models.Book;

@Repository
public interface BookRepository extends CrudRepository<Book,Long> {
	List<Book> findAll();

	List<Book> findByBorrowerIdIs(Long id);

	List<Book> findByBorrowerIdIsOrUserIdIs(Long borrowerId, Long userId);
	
}
