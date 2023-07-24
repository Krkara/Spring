package com.kristjan.webshop.controller;

import com.kristjan.webshop.entity.Person;
import com.kristjan.webshop.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping
    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    @PostMapping
    public List<Person> addPerson(@RequestBody Person person) {
        personRepository.save(person);
        return personRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public List<Person> deletePerson(@PathVariable Long id) {
        personRepository.deleteById(id);
        return personRepository.findAll();
    }

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable Long id) {
        return personRepository.findById(id).get();
    }

    @PutMapping("/{id}")
    public List<Person> updatePerson(@PathVariable Long id, @RequestBody Person person) {
        if (personRepository.existsById(id)) {
            person.setId(personRepository.findById(id).get().getId());
            personRepository.save(person);
        }
        return personRepository.findAll();
    }
}
