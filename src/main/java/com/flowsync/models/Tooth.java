package com.flowsync.models;

import com.flowsync.models.enums.ToothName;
import com.flowsync.models.enums.ToothState;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "teeth")
public class Tooth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ToothName name;
    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private ToothState state;
    @ManyToOne
    @JoinColumn(name = "dental-chart-id", nullable = false)
    private DentalChart dentalChart;
    @ManyToMany
    @JoinTable(name = "tooth_diagnoses", joinColumns = @JoinColumn(name = "tooth_id"), inverseJoinColumns = @JoinColumn(name = "diagonsis_id"))
    private Set<Diagnosis> diagnoses = new HashSet<>();
    @ManyToMany
    @JoinTable(name = "tooth_restorations", joinColumns = @JoinColumn(name = "tooth_id"), inverseJoinColumns = @JoinColumn(name = "restoration_id"))
    private Set<Restoration> restorations = new HashSet<>();
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;

    public Tooth() {
    }

    public Tooth(ToothName name, ToothState state, DentalChart dentalChart) {
        this.name = name;
        this.state = state;
        this.dentalChart = dentalChart;
    }

    public Long getId() {
        return id;
    }

    public ToothName getName() {
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

    public void addDiagnosis(Diagnosis diagnosis) {
        this.diagnoses.add(diagnosis);
    }

    public void removeDiagnosis(Diagnosis diagnosis) {
        this.diagnoses.remove(diagnosis);
    }

    public void addRestoration(Restoration restoration) {
        this.restorations.add(restoration);
    }

    public void removeRestoration(Restoration restoration) {
        this.restorations.remove(restoration);
    }
}
