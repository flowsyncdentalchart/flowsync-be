package com.flowsync.controllers;

import com.flowsync.models.Patient;
import com.flowsync.services.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping("/{id}")
    public Patient findPatientById (@PathVariable Long id) {
        return patientService.findPatientById(id);
    }

    @PutMapping("/{id}")
    public Patient updatePatient (@PathVariable Long id, @RequestBody Patient patient){
        return patientService.updatePatient(id, patient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePatient (@PathVariable Long id) {
        return patientService.deletePatient(id);
    }

}
