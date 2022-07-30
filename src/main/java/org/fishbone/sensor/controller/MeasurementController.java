package org.fishbone.sensor.controller;

import org.fishbone.sensor.dto.MeasurementDto;
import org.fishbone.sensor.model.Measurement;
import org.fishbone.sensor.model.Sensor;
import org.fishbone.sensor.service.MeasurementService;
import org.fishbone.sensor.service.SensorService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    MeasurementService measurementService;

    public MeasurementController(MeasurementService measurementService, SensorService sensorService) {
        this.measurementService = measurementService;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody MeasurementDto measurement) {

        measurementService.save(new Measurement(
            measurement.isRaining(),
            measurement.getTemperature(),
            measurement.getSensor().getName()));

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
