package com.flowsync.controllers;

import com.flowsync.dto.DentalChartDTO;
import com.flowsync.dto.DentalChartResponse;
import com.flowsync.models.User;
import com.flowsync.repositories.DentalChartRepository;
import com.flowsync.repositories.PatientRepository;
import com.flowsync.repositories.UserRepository;
import com.flowsync.services.AuthenticationService;
import com.flowsync.services.DentalChartService;
import com.flowsync.services.mappers.DentalChartMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dentalChart")
public class DentalChartController {

    private final DentalChartService dentalChartService;
    private final DentalChartRepository dentalChartRepository;
    private final PatientRepository patientRepository;
    private final UserRepository userRepository;
    private final DentalChartMapper dentalChartMapper;
    private final AuthenticationService authenticationService;

    public DentalChartController(DentalChartService dentalChartService, DentalChartRepository dentalChartRepository, PatientRepository patientRepository, UserRepository userRepository, DentalChartMapper dentalChartMapper, AuthenticationService authenticationService) {
        this.dentalChartService = dentalChartService;
        this.dentalChartRepository = dentalChartRepository;
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
        this.dentalChartMapper = dentalChartMapper;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/{patientId}")
    public ResponseEntity createDentalChart(@PathVariable Long patientId){

        User user = authenticationService.getLoggedInUser();

        Long userId = user.getId();

        DentalChartDTO dentalChartDTO = dentalChartMapper.createDentalChartDTO(patientId, userId);


        return dentalChartService.createDentalChart(dentalChartDTO);
    }

    @GetMapping("/{id}")
    public DentalChartResponse getDentalChartById(@PathVariable Long id){

        return dentalChartService.getDentalChartById(id);

    }

}
