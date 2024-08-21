package com.patients.patients.repository;

import com.patients.patients.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PatientRepository  extends JpaRepository<Patient,Long> {

    // Fetch patients older than 65 years and sort by date of birth ascending
    @Query(" SELECT p FROM Patient p where p.dateOfBirth <  :elderlyThreshold ORDER BY p.dateOfBirth Asc" )
    List<Patient> findByDateOfBirthBeforeOrderByDateOfBirthAsc(LocalDate elderlyThreshold);
}

