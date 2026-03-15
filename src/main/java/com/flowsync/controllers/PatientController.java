package com.flowsync.controllers;

import com.flowsync.models.Patient;
import com.flowsync.services.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/patient")
public class PatientController {

    private final PatientService patientService;


    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity createPatient (@RequestBody Patient patient){

        Patient newPatient = patientService.createPatient (patient);

        return ResponseEntity.ok("Patient created with id " + newPatient.getId());
    }
}
