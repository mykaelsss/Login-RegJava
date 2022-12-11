package com.javastack.spring.authentication.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javastack.spring.authentication.models.Book;
import com.javastack.spring.authentication.services.BookService;
import com.javastack.spring.authentication.services.UserService;

@Controller
@RequestMapping("/books")
public class BookController {

	private final BookService bookServ;
	private final UserService userServ;
	
	public BookController(BookService bookServ,UserService userServ) {
		this.bookServ = bookServ;
		this.userServ = userServ;
	}
	
	@GetMapping("")
	public String dashboard(HttpSession session, Model model) {
	   	if(session.getAttribute("userId") == null) {
   		return "redirect:/";
   	}
		model.addAttribute("userName", session.getAttribute("userName"));
		model.addAttribute("books", bookServ.allBooks());
		return "home.jsp";
	}
	
	@GetMapping("/new")
	public String create(HttpSession session, @ModelAttribute("book") Book book,Model model) {
		model.addAttribute("userId", session.getAttribute("userId"));
		return "create.jsp";
	}

	@PostMapping("")
	public String createBook(@Valid @ModelAttribute("book") Book book, BindingResult result) {
		if(result.hasErrors()) {
			return "create.jsp";
		}
		else {
			bookServ.create(book);
			return "redirect:/books";
		}
	}
	
	@GetMapping("/{id}")
	public String showBook(@PathVariable("id") Long id, Model model, HttpSession session) {
		model.addAttribute("book", bookServ.showBook(id));
		model.addAttribute("userId", session.getAttribute("userId"));
		return "show.jsp";
	}
	
	@DeleteMapping("/{id}/delete")
	public String deleteBook(@PathVariable("id") Long id) {
		bookServ.deleteBook(id);
		return "redirect:/books";
	}
	
	@GetMapping("/{id}/edit")
	public String editBook(@PathVariable("id") Long id, Model model) {
		Book book = bookServ.showBook(id);
		model.addAttribute(book);
		return "edit.jsp";
	}
	
	@PutMapping("/{id}/update")
	public String updateBook(@PathVariable("id") Long id, @Valid @ModelAttribute("book") Book book, BindingResult result) {
		if(result.hasErrors()) {
			return "edit.jsp";
		}
		else {
			bookServ.update(book);
			return "redirect:/books";
		}
	}
	@GetMapping("/bookmarket")
	public String bookmarket(HttpSession session, Model model) {
	 
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/logout";
		}
		
		model.addAttribute("user", userServ.findById(userId));

		List<Book> books = bookServ.unborrowedBooks(userServ.findById(userId));
		model.addAttribute("books", books);

		List<Book> myBooks = bookServ.borrowedBooks(userServ.findById(userId));
		model.addAttribute("myBooks", myBooks);
		 
		return "bookMarket.jsp";
	}
	@GetMapping("/bookmarket/{bookId}")
	public String borrowBook(@PathVariable("bookId") Long bookId, HttpSession session) {
	 
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/";
		}
		bookServ.addBorrower(bookServ.findBook(bookId), userServ.findById(userId));
		 
		return "redirect:/books/bookmarket";
	}
	@GetMapping("/bookmarket/return/{bookId}")
	public String returnBook(@PathVariable("bookId") Long bookId, HttpSession session) {
	 
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		bookServ.removeBorrower(bookServ.findBook(bookId));
		 
		return "redirect:/books/bookmarket";
	}
}
