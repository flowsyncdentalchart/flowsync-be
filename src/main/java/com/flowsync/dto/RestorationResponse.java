package com.flowsync.dto;

import com.flowsync.models.enums.Material;

public class RestorationResponse {

    private Long id;

    private String name;

    private Material material;

    public RestorationResponse() {
    }

    public RestorationResponse(Long id, String name, Material material) {
        this.id = id;
        this.name = name;
        this.material = material;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Material getMaterial() {
        return material;
    }
}
