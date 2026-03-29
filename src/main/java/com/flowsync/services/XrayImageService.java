package com.flowsync.services;

import com.flowsync.dto.XrayImageRequest;
import com.flowsync.dto.XrayImageResponse;
import com.flowsync.exceptions.ResourceNotFoundException;
import com.flowsync.models.DentalChart;
import com.flowsync.models.XrayImage;
import com.flowsync.repositories.DentalChartRepository;
import com.flowsync.repositories.PatientRepository;
import com.flowsync.repositories.UserRepository;
import com.flowsync.repositories.XrayImageRepository;
import com.flowsync.services.factories.XrayImageFactory;
import com.flowsync.services.mappers.XrayImageMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class XrayImageService {

    private final DentalChartRepository dentalChartRepository;
    private final UserRepository userRepository;
    private final XrayImageFactory xrayImageFactory;
    private final XrayImageRepository xrayImageRepository;
    private final XrayImageMapper xrayImageMapper;
    private final PatientRepository patientRepository;

    public XrayImageService(DentalChartRepository dentalChartRepository, UserRepository userRepository, XrayImageFactory xrayImageFactory, XrayImageRepository xrayImageRepository, XrayImageMapper xrayImageMapper, PatientRepository patientRepository) {
        this.dentalChartRepository = dentalChartRepository;
        this.userRepository = userRepository;
        this.xrayImageFactory = xrayImageFactory;
        this.xrayImageRepository = xrayImageRepository;
        this.xrayImageMapper = xrayImageMapper;
        this.patientRepository = patientRepository;
    }

    public XrayImageResponse uploadXray(XrayImageRequest xrayImageRequest) {
        DentalChart dentalChart = dentalChartRepository.findById(xrayImageRequest.getDentalChartId())
                .orElseThrow(() -> new ResourceNotFoundException("DentalChart not found"));
        XrayImage xrayImage = xrayImageFactory.createXrayImageEntity(dentalChart, xrayImageRequest.getFilePath());
        XrayImage savedXrayImage = xrayImageRepository.save(xrayImage);
        xrayImageMapper.toResponse(savedXrayImage);
        return xrayImageMapper.toResponse(savedXrayImage);
    }

    public XrayImageResponse getXrayImageById(Long xrayImageId) {
        XrayImage xrayImage = xrayImageRepository.findById(xrayImageId)
                .orElseThrow(() -> new ResourceNotFoundException("Xray image not found"));
        XrayImageResponse xrayImageResponse = xrayImageMapper.toResponse(xrayImage);
        return xrayImageResponse;
    }

    public List<XrayImageResponse> getAllXrayByDentalChartId(Long dentalChartId) {
       dentalChartRepository.findById(dentalChartId)
                .orElseThrow(() -> new ResourceNotFoundException("Dental chart not found"));
        List<XrayImage> xrayImageList = xrayImageRepository.findAllByDentalChartId(dentalChartId);
        List<XrayImageResponse> xrayImageResponseList = xrayImageMapper.toResponse(xrayImageList);
        return xrayImageResponseList;
    }

    public List<XrayImageResponse> getAllXrayByDentalByPatientId(Long patientId) {
        patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
        List<XrayImage> xrayImageList = xrayImageRepository.findAllByDentalChart_Patient_Id(patientId);
        List<XrayImageResponse> xrayImageResponseList = xrayImageMapper.toResponse(xrayImageList);
        return xrayImageResponseList;
    }
}
