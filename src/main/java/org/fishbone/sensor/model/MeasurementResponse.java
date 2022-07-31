package org.fishbone.sensor.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.fishbone.sensor.dto.MeasurementDto;

@Data
@AllArgsConstructor
public class MeasurementResponse {

    private List<MeasurementDto> measurementList;
}
