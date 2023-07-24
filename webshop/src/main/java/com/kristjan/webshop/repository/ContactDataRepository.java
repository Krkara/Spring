package com.kristjan.webshop.repository;

import com.kristjan.webshop.entity.ContactData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactDataRepository extends JpaRepository<ContactData, Long> {
}
