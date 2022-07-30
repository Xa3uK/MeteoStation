package org.fishbone.sensor.dto;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fishbone.sensor.model.Sensor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeasurementDto {

    @NotNull
    @Pattern(regexp = "^true$|^false$", message = "allowed input: true or false")
    private String raining;

    @NotNull(message = "Field value should be not empty")
    @DecimalMin(value = "-100.0", message = "Min value is -100")
    @DecimalMax(value = "100.0",  message = "Max value is 100")
    private Double value;

    @NotNull(message = "Field Sensor should be not empty")
    private Sensor sensor;
}
