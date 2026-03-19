package com.flowsync.services;


import com.flowsync.models.Patient;
import com.flowsync.repositories.PatientRepository;
import org.springframework.http.ResponseEntity;
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

    public Patient findPatientById(Long id) {

        return patientRepository.findPatientById(id);

    }

    public Patient updatePatient(Long id, Patient patient) {
        Patient updatedPatient = patientRepository.findPatientById(id);

        if (updatedPatient != null) {
            if (patient.getFirstName() != null) {
                updatedPatient.setFirstName(patient.getFirstName());
            }
            if (patient.getLastName() != null) {
                updatedPatient.setLastName(patient.getLastName());
            }
        } else {
            throw new RuntimeException("Patient not found");
        }

        return patientRepository.save(updatedPatient);
    }

    public ResponseEntity deletePatient(Long id) {
        patientRepository.deleteById(id);
        return ResponseEntity.ok("Deleted successfully");
    }

}
