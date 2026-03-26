package com.flowsync.models;

import com.flowsync.models.enums.ToothState;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "teeth")
public class Tooth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private ToothState state;
    @ManyToOne
    @JoinColumn(name = "dental-chart-id", nullable = false)
    private DentalChart dentalChart;
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;

    public Tooth() {
    }

    public Tooth(String name, ToothState state, DentalChart dentalChart) {
        this.name = name;
        this.state = state;
        this.dentalChart = dentalChart;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ToothState getState() {
        return state;
    }

    public void setState(ToothState state) {
        this.state = state;
    }

    public DentalChart getDentalChart() {
        return dentalChart;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}
