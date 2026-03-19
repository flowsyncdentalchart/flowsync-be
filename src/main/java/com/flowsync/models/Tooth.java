package com.flowsync.models;

import jakarta.persistence.GeneratedValue;

public class Tooth {

    @GeneratedValue
    private Long id;

    private String name;

    private String state;


}
