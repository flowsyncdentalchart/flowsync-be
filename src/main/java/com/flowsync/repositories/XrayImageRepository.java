package com.flowsync.repositories;

import com.flowsync.models.XrayImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface XrayImageRepository extends JpaRepository<XrayImage, Long> {

    List<XrayImage> findByPatientId(Long patientId);

}
