package com.javastack.spring.authentication.controllers;

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

@Controller
@RequestMapping("/books")
public class BookController {

	private final BookService bookServ;
	
	public BookController(BookService bookServ) {
		this.bookServ = bookServ;
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
}
