package com.flowsync.services.mappers;

import com.flowsync.dto.PatientDTO;
import com.flowsync.dto.PatientResponseDTO;
import com.flowsync.models.Patient;
import org.springframework.stereotype.Service;

@Service
public class PatientMapper {

    public PatientDTO createPatientDTOFromPatient(Patient patient) {

        return new PatientDTO(patient.getFirstName(), patient.getLastName());

    }

    public void validateFields(Patient patient) {
        if (patient.getFirstName() == null || patient.getLastName() == null) {
            throw new IllegalArgumentException("firstName and lastName are required");
        }
    }

    public Patient createPatientFromPatientDTO(PatientDTO patientDTO) {
        return new Patient(patientDTO.getFirstName(), patientDTO.getLastName());
    }

    public PatientResponseDTO createPatientResponseDTOFromPatient(Patient patient) {

        return new PatientResponseDTO(patient.getId(), patient.getFirstName(), patient.getLastName());




    }

}
