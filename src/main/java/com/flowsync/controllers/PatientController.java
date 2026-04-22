package com.flowsync.controllers;

import com.flowsync.dto.PatientRequest;
import com.flowsync.dto.PatientResponse;
import com.flowsync.models.Patient;
import com.flowsync.services.PatientService;
import com.flowsync.services.mappers.PatientMapper;
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
@RequestMapping("/api/patient")
public class PatientController {

    private final PatientService patientService;
    private final PatientMapper patientMapper;

    public PatientController(PatientService patientService, PatientMapper patientMapper) {
        this.patientService = patientService;
        this.patientMapper = patientMapper;
    }

    @PostMapping
    public ResponseEntity createPatient(@RequestBody Patient patient) {

        patientMapper.validateFields(patient);

        PatientRequest patientRequest = patientMapper.createPatientDTOFromPatient(patient);

        Patient newPatient = patientService.createPatient(patientRequest);

        return ResponseEntity.ok("Patient created with id " + newPatient.getId());
    }

    @GetMapping("/{id}")
    public PatientResponse findPatientById(@PathVariable Long id) {
        return patientService.findPatientById(id);
    }

    @GetMapping
    public ResponseEntity<List<PatientResponse>> getAllPatients() {
        List<PatientResponse> patients = patientService.getAllPatients();
        return ResponseEntity.ok(patients);
    }

    @PutMapping("/{id}")
    public PatientResponse updatePatient(@PathVariable Long id, @RequestBody PatientRequest patientRequest) {
        return patientService.updatePatient(id, patientRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePatient(@PathVariable Long id) {
        return patientService.deletePatient(id);
    }

}
