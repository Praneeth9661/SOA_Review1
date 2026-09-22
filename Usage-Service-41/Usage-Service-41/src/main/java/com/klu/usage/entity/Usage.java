package com.klu.usage.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "usage_records")
public class Usage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usageId;

    @Column(nullable = false)
    private Long meterId;

    @Column(nullable = false)
    private Double consumptionUnits;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    public Usage() {
    }

    public Usage(Long usageId,
                 Long meterId,
                 Double consumptionUnits,
                 LocalDateTime timestamp) {

        this.usageId = usageId;
        this.meterId = meterId;
        this.consumptionUnits = consumptionUnits;
        this.timestamp = timestamp;
    }

    public Long getUsageId() {
        return usageId;
    }

    public void setUsageId(Long usageId) {
        this.usageId = usageId;
    }

    public Long getMeterId() {
        return meterId;
    }

    public void setMeterId(Long meterId) {
        this.meterId = meterId;
    }

    public Double getConsumptionUnits() {
        return consumptionUnits;
    }

    public void setConsumptionUnits(Double consumptionUnits) {
        this.consumptionUnits = consumptionUnits;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}