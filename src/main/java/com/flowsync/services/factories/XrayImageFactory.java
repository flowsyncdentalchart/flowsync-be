package com.flowsync.services.factories;

import com.flowsync.models.DentalChart;
import com.flowsync.models.User;
import com.flowsync.models.XrayImage;
import com.flowsync.services.AuthenticationService;
import org.springframework.stereotype.Component;

@Component
public class XrayImageFactory {

    private AuthenticationService authenticationService;

    public XrayImageFactory(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public XrayImage createXrayImageEntity(DentalChart dentalChart, String filePath) {

        User user = authenticationService.getLoggedInUser();

        XrayImage xrayImage = new XrayImage();
        xrayImage.setDentalChart(dentalChart);
        xrayImage.setUser(user);
        xrayImage.setFilePath(filePath);
        return xrayImage;
    }
}
