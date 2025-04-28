package org.example.sheriev.controllers;

import org.example.sheriev.DAO.PersonDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("people", personDAO.getPeople());
        return "people/index";
    }

    @GetMapping("{id}")
    public String showPerson(@PathVariable("id") int id,
                             Model model) {
        model.addAttribute("person", personDAO.getPerson(id));
        return "people/person";
    }
}
