package org.fishbone.sensor.controller;

import java.util.List;
import org.fishbone.sensor.dto.MeasurementDto;
import org.fishbone.sensor.model.Measurement;
import org.fishbone.sensor.service.MeasurementService;
import org.fishbone.sensor.service.SensorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
            measurement.getValue(),
            measurement.getSensor().getName()));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping
    public List<Measurement> getAll() {
        return measurementService.findAll();
    }

    @GetMapping("/rainyDaysCount")
    public int getRainyDays() {
        return (int) measurementService.findAll()
            .stream()
            .filter(Measurement::isRaining)
            .count();
    }
}
