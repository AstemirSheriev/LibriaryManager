package org.example.sheriev.controllers;

import org.example.sheriev.DAO.BookDAO;
import org.example.sheriev.DAO.PersonDAO;
import org.example.sheriev.models.Book;
import org.example.sheriev.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("books", bookDAO.getBooks());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id,
                           Model model,
                           @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookDAO.getBook(id));

        Optional<Person> owner = bookDAO.getBookOwner(id);

        if (owner.isPresent()) {
            model.addAttribute("owner", owner.get());
        }
        else {
            model.addAttribute("people", personDAO.getPeople());
        }

        return "books/book";
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
    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.getBook(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id,
                         @ModelAttribute("book") Book book){
        bookDAO.update(book, id);
        return "redirect:/books";
    }
    @PatchMapping("/{id}/assign")
    public String newPerson(@PathVariable("id") int id,
                            @ModelAttribute("person") Person person,
                            BindingResult bindingResult){
        bookDAO.assign(id, person);
        return "redirect:/books";
    }
    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id) {
        bookDAO.release(id);
        return "redirect:/books";
    }
    @DeleteMapping("/{id}/delete")
    public String deleteBook(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }
}
