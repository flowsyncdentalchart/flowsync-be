package com.flowsync.services;


import com.flowsync.models.Patient;
import com.flowsync.repositories.PatientRepository;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient createPatient(Patient patient) {

        return patientRepository.save(patient);
    }
}
