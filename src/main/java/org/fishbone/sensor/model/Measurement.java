package org.fishbone.sensor.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "measurements")
public class Measurement {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "raining")
    private boolean raining;

    @Column(name = "temperature")
    private double temperature;

    @Column(name = "sensor_name")
    private String sensorName;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Measurement(boolean raining, double temperature, String sensorName, LocalDateTime createdAt) {
        this.raining = raining;
        this.temperature = temperature;
        this.sensorName = sensorName;
        this.createdAt = createdAt;
    }
}
