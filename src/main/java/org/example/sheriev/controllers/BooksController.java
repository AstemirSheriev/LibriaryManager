package org.example.sheriev.controllers;

import org.example.sheriev.DAO.BookDAO;
import org.example.sheriev.models.Book;
import org.example.sheriev.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookDAO bookDAO;

    @Autowired
    public BooksController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("books", bookDAO.getBooks());
        return "books/index";
    }

    @GetMapping("/new")
    public String newPerson(Model model, @ModelAttribute("book") Book book) {
        return "books/new";
    }
    @PostMapping
    public String newPerson(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        bookDAO.addBook(book);
        return "redirect:/books";
    }
}
