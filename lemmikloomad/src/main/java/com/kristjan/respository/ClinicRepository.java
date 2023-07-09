package com.kristjan.respository;

import com.kristjan.entity.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicRepository extends JpaRepository<Clinic, String> {

}
