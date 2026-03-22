package com.flowsync.services;

import com.flowsync.dto.XrayImageResponseDTO;
import com.flowsync.exceptions.ResourceNotFoundException;
import com.flowsync.models.DentalChart;
import com.flowsync.models.User;
import com.flowsync.repositories.DentalChartRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class XrayImageService {

    private final DentalChartRepository dentalChartRepository;

    public XrayImageService(DentalChartRepository dentalChartRepository) {
        this.dentalChartRepository = dentalChartRepository;
    }

//    public ResponseEntity<XrayImageResponseDTO> uploadXrayImageToDentalChart(String dentalChartId){
//        DentalChart dentalChart = dentalChartRepository.findById(dentalChartId).orElseThrow(() -> new ResourceNotFoundException("Dental chart with id " + id + " not found"));
//       // XrayImageResponseDTO xrayImageResponseDTO = x
//        return ResponseEntity.ok()
    }
}
