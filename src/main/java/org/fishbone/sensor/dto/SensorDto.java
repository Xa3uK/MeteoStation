package org.fishbone.sensor.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SensorDto {

    @NotEmpty(message = "name should be not empty")
    @Size(min = 3, max = 30, message = "name should be between 3 and 30 characters")
    String name;
}
