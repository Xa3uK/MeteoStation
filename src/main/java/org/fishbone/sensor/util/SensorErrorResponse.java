package org.fishbone.sensor.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SensorErrorResponse {
    private String message;
    private long timestamp;
}
