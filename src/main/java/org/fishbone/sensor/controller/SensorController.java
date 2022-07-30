package org.fishbone.sensor.controller;

import org.fishbone.sensor.dto.SensorDto;
import org.fishbone.sensor.model.Sensor;
import org.fishbone.sensor.service.SensorService;
import org.fishbone.sensor.util.SensorDuplicateException;
import org.fishbone.sensor.util.SensorErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sensor")
public class SensorController {

    SensorService sensorService;

    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registration(@RequestBody SensorDto sensor) {
        sensorService.save(new Sensor(sensor.getName()));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorDuplicateException e) {
        SensorErrorResponse response = new SensorErrorResponse(
            "This sensor already registered",
            System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
