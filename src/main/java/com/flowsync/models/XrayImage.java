package com.flowsync.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "xray_images")
public class XrayImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dental_chart_id")
    @NotNull(message = "Xray image dental chart reference must not be null")
    private DentalChart dentalChart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @NotNull(message = "Xray image user reference must not be null")
    private User user;

    @Column(nullable = false, unique = true)
    @Size(max = 500, message = "File path too long")
    @NotNull(message = "Xray image file path must not be null")
    @Pattern(regexp = "^(https?:\\/\\/.*|\\/.*)$", message = "File path must be a valid URL or absolute path")
    private String filePath;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public XrayImage() {
    }

    public XrayImage(Long id, DentalChart dentalChart, User user, String filePath, LocalDateTime createdAt) {
        this.id = id;
        this.dentalChart = dentalChart;
        this.user = user;
        this.filePath = filePath;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DentalChart getDentalChart() {
        return dentalChart;
    }

    public void setDentalChart(DentalChart dentalChart) {
        this.dentalChart = dentalChart;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User takenBy) {
        this.user = takenBy;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
    }

}
