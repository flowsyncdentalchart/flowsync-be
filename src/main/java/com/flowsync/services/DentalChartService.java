package com.flowsync.services;


import com.flowsync.dto.DentalChartDTO;
import com.flowsync.dto.DentalChartResponse;
import com.flowsync.exceptions.ResourceNotFoundException;
import com.flowsync.models.DentalChart;
import com.flowsync.repositories.DentalChartRepository;
import com.flowsync.services.mappers.DentalChartMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DentalChartService {

    private final DentalChartRepository dentalChartRepository;
    private final DentalChartMapper dentalChartMapper;

    public DentalChartService(DentalChartRepository dentalChartRepository, DentalChartMapper dentalChartMapper){
        this.dentalChartRepository = dentalChartRepository;
        this.dentalChartMapper = dentalChartMapper;
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
}
