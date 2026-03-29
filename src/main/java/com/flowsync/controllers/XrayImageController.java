package com.flowsync.controllers;

import com.flowsync.dto.XrayImageRequest;
import com.flowsync.dto.XrayImageResponse;
import com.flowsync.services.XrayImageService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/xrays")
public class XrayImageController {

    private final XrayImageService xrayImageService;

    public XrayImageController(XrayImageService xrayImageService) {
        this.xrayImageService = xrayImageService;

    }

    @PostMapping
    public ResponseEntity<XrayImageResponse> uploadXray(
            @RequestBody @Valid XrayImageRequest xrayImageRequest) {

        XrayImageResponse xrayImageResponse = xrayImageService.uploadXray(xrayImageRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(xrayImageResponse);
    }

    @GetMapping("/{id}") ResponseEntity<XrayImageResponse> getXrayById(
            @PathVariable Long id) {
        XrayImageResponse xrayImageResponse = xrayImageService.getXrayImageById(id);
        return ResponseEntity.ok(xrayImageResponse);
    }

    @GetMapping("/dental-chart/{dentalChartId}") ResponseEntity<List<XrayImageResponse>> getAllXrayByDentalChartId(
            @PathVariable Long dentalChartId) {
        List<XrayImageResponse> xrayImageResponse = xrayImageService.getAllXrayByDentalChartId(dentalChartId);
        return ResponseEntity.ok(xrayImageResponse);
    }

    @GetMapping("/patients/{patientId}") ResponseEntity<List<XrayImageResponse>> getAllXrayByDentalByPatientId(
            @PathVariable Long patientId) {
        List<XrayImageResponse> xrayImageResponse = xrayImageService.getAllXrayByDentalByPatientId(patientId);
        return ResponseEntity.ok(xrayImageResponse);
    }



}