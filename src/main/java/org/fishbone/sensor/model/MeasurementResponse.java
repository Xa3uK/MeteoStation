package org.fishbone.sensor.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MeasurementResponse {

    private List<Measurement> measurementList;
}
