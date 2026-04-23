package com.flowsync.controllers;

import com.flowsync.dto.DentalChartRequest;
import com.flowsync.dto.DentalChartResponse;
import com.flowsync.models.User;
import com.flowsync.repositories.DentalChartRepository;
import com.flowsync.repositories.PatientRepository;
import com.flowsync.repositories.UserRepository;
import com.flowsync.services.AuthenticationService;
import com.flowsync.services.DentalChartService;
import com.flowsync.services.mappers.DentalChartMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dentalChart")
public class DentalChartController {

    private final DentalChartService dentalChartService;
    private final DentalChartMapper dentalChartMapper;
    private final AuthenticationService authenticationService;

    public DentalChartController(DentalChartService dentalChartService, DentalChartRepository dentalChartRepository,
            PatientRepository patientRepository, UserRepository userRepository, DentalChartMapper dentalChartMapper,
            AuthenticationService authenticationService) {
        this.dentalChartService = dentalChartService;
        this.dentalChartMapper = dentalChartMapper;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/{patientId}")
    public DentalChartResponse createDentalChart(@PathVariable Long patientId) {

        User user = authenticationService.getLoggedInUser();

        Long userId = user.getId();

        DentalChartRequest dentalChartRequest = dentalChartMapper.createDentalChartDTO(patientId, userId);

        return dentalChartService.createDentalChart(dentalChartRequest);
    }

    @GetMapping("/{id}")
    public DentalChartResponse getDentalChartById(@PathVariable Long id) {

        return dentalChartService.getDentalChartById(id);

    }

    @GetMapping("/patient/{id}")
    public List<DentalChartResponse> getDentalChartsByPatientId(@PathVariable Long id) {
        return dentalChartService.getDentalChartsByPatientId(id);
    }

    @PutMapping("/{id}")
    public DentalChartResponse updateDentalChart(@PathVariable Long id,
            @RequestBody DentalChartRequest dentalChartRequest) {

        return dentalChartService.updateDentalChart(id, dentalChartRequest);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteDentalChart(@PathVariable Long id) {

        return dentalChartService.deleteDentalChart(id);

    }

}
