package com.flowsync.controllers;

import com.flowsync.models.DentalChart;
import com.flowsync.repositories.PatientRepository;
import com.flowsync.repositories.UserRepository;
import com.flowsync.services.DentalChartService;
import com.flowsync.services.mappers.DentalChartMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dentalChart")
public class DentalChartController {

    private final DentalChartService dentalChartService;
    private final PatientRepository patientRepository;
    private final UserRepository userRepository;
    private final DentalChartMapper dentalChartMapper;

    public DentalChartController(DentalChartService dentalChartService, PatientRepository patientRepository, UserRepository userRepository, DentalChartMapper dentalChartMapper) {
        this.dentalChartService = dentalChartService;
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
        this.dentalChartMapper = dentalChartMapper;
    }

    @PostMapping
    public ResponseEntity createDentalChart(@PathVariable Long patientId){



        return dentalChartService.createDentalChart(dentalChartDTO);
    }

}
