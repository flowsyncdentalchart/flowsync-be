package com.flowsync.services;


import com.flowsync.dto.DentalChartDTO;
import com.flowsync.dto.DentalChartResponse;
import com.flowsync.exceptions.ResourceNotFoundException;
import com.flowsync.models.DentalChart;
import com.flowsync.repositories.DentalChartRepository;
import com.flowsync.repositories.PatientRepository;
import com.flowsync.repositories.UserRepository;
import com.flowsync.services.mappers.DentalChartMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DentalChartService {

    private final DentalChartRepository dentalChartRepository;
    private final DentalChartMapper dentalChartMapper;
    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    public DentalChartService(DentalChartRepository dentalChartRepository, DentalChartMapper dentalChartMapper, PatientRepository patientRepository, UserRepository userRepository){
        this.dentalChartRepository = dentalChartRepository;
        this.dentalChartMapper = dentalChartMapper;
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity createDentalChart(DentalChartDTO dentalChartDTO) {

        DentalChart dentalChart = dentalChartRepository.save(dentalChartMapper.createDentalChartFromDTO(dentalChartDTO));
        return ResponseEntity.ok("Dental Chart created with id " + dentalChart.getId());
    }

    public DentalChartResponse getDentalChartById(Long id) {
        DentalChart dentalChart = dentalChartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dental Chart with id " + id + " not found"));

        return new DentalChartResponse(
                dentalChart.getId(),
                dentalChart.getPatient().getId(),
                dentalChart.getPatient().getFirstName(),
                dentalChart.getPatient().getLastName(),
                dentalChart.getUser().getId(),
                dentalChart.getUser().getFirstName(),
                dentalChart.getUser().getLastName(),
                dentalChart.getCreatedAt(),
                dentalChart.getUpdatedAt());

    }

    public DentalChartResponse updateDentalChart(Long id, DentalChartDTO dentalChartDTO) {
        DentalChart updatedDentalChart = dentalChartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dental Chart with id " + id + " not found"));

        if (dentalChartDTO.getPatientId() != null) {
            updatedDentalChart.setPatient(patientRepository.findById(dentalChartDTO.getPatientId()).orElseThrow( () -> new ResourceNotFoundException("Patient with id " + dentalChartDTO.getPatientId() + " not found")));
        }

        if (dentalChartDTO.getUserId() != null) {
            updatedDentalChart.setUser(userRepository.findById(dentalChartDTO.getUserId()).orElseThrow( () -> new ResourceNotFoundException("User with id " + dentalChartDTO.getUserId() + " not found")));
        }

        dentalChartRepository.save(updatedDentalChart);

        return new DentalChartResponse(
                updatedDentalChart.getId(),
                updatedDentalChart.getPatient().getId(),
                updatedDentalChart.getPatient().getFirstName(),
                updatedDentalChart.getPatient().getLastName(),
                updatedDentalChart.getUser().getId(),
                updatedDentalChart.getUser().getFirstName(),
                updatedDentalChart.getUser().getLastName(),
                updatedDentalChart.getCreatedAt(),
                updatedDentalChart.getUpdatedAt());

    }

    public ResponseEntity deleteDentalChart (Long id) {
        DentalChart dentalChart = dentalChartRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Dental Chart with id " + id + " not found"));

        dentalChartRepository.delete(dentalChart);

        return ResponseEntity.ok("Dental Chart deleted with id " + id);
    }

}
