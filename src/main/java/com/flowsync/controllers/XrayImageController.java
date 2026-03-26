package com.flowsync.controllers;

import com.flowsync.dto.XrayImageRequest;
import com.flowsync.dto.XrayImageResponse;
import com.flowsync.services.XrayImageService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}