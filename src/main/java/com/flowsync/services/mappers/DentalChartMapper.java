package com.flowsync.services.mappers;

import com.flowsync.dto.DentalChartDTO;
import com.flowsync.exceptions.ResourceNotFoundException;
import com.flowsync.models.DentalChart;
import com.flowsync.models.Patient;
import com.flowsync.models.User;
import com.flowsync.repositories.PatientRepository;
import com.flowsync.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class DentalChartMapper {

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    public DentalChartMapper(PatientRepository patientRepository, UserRepository userRepository) {
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
    }

    public DentalChartDTO createDentalChartDTO(Long patientId, Long userId) {
        return new DentalChartDTO(patientId, userId);
    }

    public void validateFields(DentalChart dentalChart) {
        if (dentalChart.getPatient().getId() == null || dentalChart.getUser().getId() == null) {
            throw new IllegalArgumentException("DentalChart must have patientId and caregiverId");
        }
    }

    public DentalChart createDentalChartFromDTO(DentalChartDTO dentalChartDTO) {

        Patient patient = patientRepository.findById(dentalChartDTO.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        User user = userRepository.findById(dentalChartDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return new DentalChart(patient, user);
    }
}
