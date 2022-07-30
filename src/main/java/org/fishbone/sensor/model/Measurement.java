package org.fishbone.sensor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Measurement {

    private boolean raining;
    private double temperature;
    private Sensor sensor;
}
