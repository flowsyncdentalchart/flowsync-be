package com.flowsync.controllers;

import com.flowsync.dto.XrayImageRequest;
import com.flowsync.dto.XrayImageResponse;
import com.flowsync.services.XrayImageService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dental-charts")
public class XrayImageController {

    private final XrayImageService xrayImageService;

    public XrayImageController(XrayImageService xrayImageService) {
        this.xrayImageService = xrayImageService;

    }

    @PostMapping("{dentalChartId}/xrays")
    public ResponseEntity<XrayImageResponse> uploadXray(
            @PathVariable Long dentalChartId,
            @RequestBody @Valid XrayImageRequest xrayImageRequest) {

        XrayImageResponse xrayImageResponse = xrayImageService.uploadXray(dentalChartId, xrayImageRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(xrayImageResponse);
    }
}