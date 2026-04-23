package com.flowsync.services;

import com.flowsync.dto.PatientRequest;
import com.flowsync.dto.PatientResponse;
import com.flowsync.exceptions.ResourceNotFoundException;
import com.flowsync.models.Patient;
import com.flowsync.repositories.PatientRepository;
import com.flowsync.services.mappers.PatientMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public PatientService(PatientRepository patientRepository, PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }

    public Patient createPatient(PatientRequest patientRequest) {

        return patientRepository.save(patientMapper.createPatientFromPatientDTO(patientRequest));
    }

    public PatientResponse findPatientById(Long id) {
        Patient tempPatient = patientRepository.findPatientById(id);

        if (tempPatient == null) {
            throw new ResourceNotFoundException("Patient not found");
        }

        return patientMapper.createPatientResponseDTOFromPatient(tempPatient);

    }

    public Page<PatientResponse> getAllPatients(int page, int size, String search) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Patient> result;

        if (search == null || search.isBlank()) {
            result = patientRepository.findAll(pageable);
        } else {
            result = patientRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(search, search,
                    pageable);
        }

        return result.map(patientMapper::createPatientResponseDTOFromPatient);
    }

    public PatientResponse updatePatient(Long id, PatientRequest patientRequest) {

        Patient updatedPatient = patientRepository.findPatientById(id);

        if (updatedPatient != null) {
            if (patientRequest.getFirstName() == null && patientRequest.getLastName() == null) {
                throw new IllegalArgumentException("Either first name or last name must be provided");
            } else {
                if (patientRequest.getFirstName() != null) {
                    updatedPatient.setFirstName(patientRequest.getFirstName());
                }
                if (patientRequest.getLastName() != null) {
                    updatedPatient.setLastName(patientRequest.getLastName());
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

    public List<PatientResponse> getRecentPatients() {
        return patientRepository.findTop5ByOrderByCreatedAtDesc().stream()
                .map(patientMapper::createPatientResponseDTOFromPatient).toList();
    }
}
