package com.javastack.spring.authentication.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.javastack.spring.authentication.models.Book;
import com.javastack.spring.authentication.repositories.BookRepository;

@Service
public class BookService {
	
	private final BookRepository bookRepo;
	
	public BookService(BookRepository bookRepo) {
		this.bookRepo = bookRepo;
	}
	
	//Get All
	public List<Book> allBooks(){
		return bookRepo.findAll();
	}
	
	//Get one
	public Book showBook(Long id) {
		Optional<Book> optionalBook = bookRepo.findById(id);
		if(optionalBook.isPresent()) {
			return optionalBook.get();
		}
		else {
			return optionalBook.orElse(null);
		}
	}
	
	//Create
	public Book create(Book book) {
		return bookRepo.save(book);
	}
	
	//Update
	public Book update(Book book) {
		return bookRepo.save(book);
	}
	
	//Delete
	public void deleteBook(Long id) {
		Optional<Book> optionalBook = bookRepo.findById(id);
		if(optionalBook.isPresent()) {
			bookRepo.deleteById(id);
		}
	}
}
