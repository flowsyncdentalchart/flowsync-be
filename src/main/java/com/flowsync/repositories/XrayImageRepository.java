package com.flowsync.repositories;

import com.flowsync.models.XrayImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface XrayImageRepository extends JpaRepository<XrayImage, Long> {

}
