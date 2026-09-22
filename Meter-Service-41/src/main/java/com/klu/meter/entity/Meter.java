package com.klu.meter.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "meters")
public class Meter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long meterId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String status;

    public Meter() {
    }

    public Meter(Long meterId, Long userId,
                  String location, String status) {
        this.meterId = meterId;
        this.userId = userId;
        this.location = location;
        this.status = status;
    }

    public Long getMeterId() {
        return meterId;
    }

    public void setMeterId(Long meterId) {
        this.meterId = meterId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}