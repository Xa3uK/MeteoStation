package org.fishbone.sensor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fishbone.sensor.model.Sensor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeasurementDto {

    private boolean raining;
    private double temperature;
    private Sensor sensor;
}
