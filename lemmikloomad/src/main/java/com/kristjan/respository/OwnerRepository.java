package com.kristjan.respository;

import com.kristjan.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OwnerRepository extends JpaRepository<Owner, String> {
    Owner findByPersonalCode(String code);
    @Query("SELECT o FROM Owner o WHERE SIZE(o.pets) > :count")
    List<Owner> findAllByPetsGreaterThan(int count);
}
