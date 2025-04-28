package org.example.sheriev.controllers;

import org.example.sheriev.DAO.PersonDAO;
import org.example.sheriev.models.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @GetMapping("/new")
    public String newPerson(Model model, @ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String newPerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        personDAO.addPerson(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(@PathVariable("id") int id, Model model) {
        Person person = personDAO.getPerson(id);
        model.addAttribute("person", person);
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String editPerson(@PathVariable("id") int id,
                             @ModelAttribute("person") Person person){
        personDAO.edit(person, id);
        return "redirect:/people";
    }
}
