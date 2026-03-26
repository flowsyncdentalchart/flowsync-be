package com.flowsync.services.mappers;

import com.flowsync.dto.PatientRequest;
import com.flowsync.dto.PatientResponse;
import com.flowsync.models.Patient;
import org.springframework.stereotype.Service;

@Service
public class PatientMapper {

    public PatientRequest createPatientDTOFromPatient(Patient patient) {

        return new PatientRequest(patient.getFirstName(), patient.getLastName());

    }

    public void validateFields(Patient patient) {
        if (patient.getFirstName() == null || patient.getLastName() == null) {
            throw new IllegalArgumentException("firstName and lastName are required");
        }
    }

    public Patient createPatientFromPatientDTO(PatientRequest patientRequest) {
        return new Patient(patientRequest.getFirstName(), patientRequest.getLastName());
    }

    public PatientResponse createPatientResponseDTOFromPatient(Patient patient) {

        return new PatientResponse(patient.getId(), patient.getFirstName(), patient.getLastName());

    }

}
