package com.flowsync.services;

import com.flowsync.dto.PatientDTO;
import com.flowsync.dto.PatientResponseDTO;
import com.flowsync.exceptions.ResourceNotFoundException;
import com.flowsync.models.Patient;
import com.flowsync.repositories.PatientRepository;
import com.flowsync.services.mappers.PatientMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public PatientService(PatientRepository patientRepository, PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }

    public Patient createPatient(PatientDTO patientDTO) {

        return patientRepository.save(patientMapper.createPatientFromPatientDTO(patientDTO));
    }

    public PatientResponseDTO getPatientById(Long id) {
        Patient tempPatient = patientRepository.findPatientById(id);

        if (tempPatient == null) {
            throw new ResourceNotFoundException("Patient not found");
        }

        return patientMapper.createPatientResponseDTOFromPatient(tempPatient);

    }

    public PatientResponseDTO updatePatient(Long id, PatientDTO patientDTO) {

        Patient updatedPatient = patientRepository.findPatientById(id);

        if (updatedPatient != null) {
            if (patientDTO.getFirstName() == null && patientDTO.getLastName() == null) {
                throw new IllegalArgumentException("Either first name or last name must be provided");
            } else {
                if (patientDTO.getFirstName() != null) {
                    updatedPatient.setFirstName(patientDTO.getFirstName());
                }
                if (patientDTO.getLastName() != null) {
                    updatedPatient.setLastName(patientDTO.getLastName());
                }
            }
        } else {
            throw new ResourceNotFoundException("Patient not found");
        }

        patientRepository.save(updatedPatient);

        return patientMapper.createPatientResponseDTOFromPatient(updatedPatient);
    }

    public ResponseEntity deletePatient(Long id) {

        Patient tempPatient = patientRepository.findPatientById(id);

        if (tempPatient == null) {
            throw new ResourceNotFoundException("Patient not found");
        }

        patientRepository.deleteById(id);
        return ResponseEntity.ok("Deleted successfully");
    }

}
