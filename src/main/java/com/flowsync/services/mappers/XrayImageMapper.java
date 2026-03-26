package com.flowsync.services.mappers;

import com.flowsync.dto.XrayImageRequest;
import com.flowsync.dto.XrayImageResponse;
import com.flowsync.models.DentalChart;
import com.flowsync.models.User;
import com.flowsync.models.XrayImage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class XrayImageMapper {

    public XrayImage toEntity(XrayImageRequest request, DentalChart dentalChart, User takenBy) {
        XrayImage xrayImage = new XrayImage();
        xrayImage.setDentalChart(dentalChart);
        xrayImage.setUser(takenBy);
        xrayImage.setFilePath(request.getFilePath());
        return xrayImage;
    }

    public XrayImageResponse toResponse(XrayImage xrayImage) {
        XrayImageResponse response = new XrayImageResponse();
        response.setId(xrayImage.getId());
        response.setDentalChartId(xrayImage.getDentalChart().getId());
        response.setUserId(xrayImage.getId());
        response.setFilePath(xrayImage.getFilePath());
        response.setCreatedAt(xrayImage.getCreatedAt());
        return response;
    }

    public List<XrayImageResponse> toResponse (List<XrayImage> xrayImages) {
        List<XrayImageResponse> xrayImageResponses = new ArrayList<>();
        for(XrayImage xrayImage : xrayImages) {
            xrayImageResponses.add(toResponse(xrayImage));
        }
        return xrayImageResponses;
    }
}
