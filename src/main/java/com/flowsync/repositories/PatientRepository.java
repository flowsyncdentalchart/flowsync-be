package com.flowsync.repositories;

import com.flowsync.models.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Patient findPatientById(long id);

    Page<Patient> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            String first, String last, Pageable pageable
    );

    List<Patient> findTop5ByOrderByCreatedAtDesc();

}
