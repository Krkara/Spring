package com.kristjan.webshop.repository;

import com.kristjan.webshop.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByPersonalCode(String personalCode);
}
