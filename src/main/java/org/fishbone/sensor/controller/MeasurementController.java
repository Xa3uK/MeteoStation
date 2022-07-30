package org.fishbone.sensor.controller;

import java.util.List;
import javax.validation.Valid;
import org.fishbone.sensor.dto.MeasurementDto;
import org.fishbone.sensor.model.Measurement;
import org.fishbone.sensor.service.MeasurementService;
import org.fishbone.sensor.service.SensorService;
import org.fishbone.sensor.util.MeasurementErrorResponse;
import org.fishbone.sensor.util.MeasurementNotCreatedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    MeasurementService measurementService;
    SensorService sensorService;

    public MeasurementController(MeasurementService measurementService, SensorService sensorService) {
        this.measurementService = measurementService;
        this.sensorService = sensorService;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid MeasurementDto measurement,
                                          BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                    .append(" - ").append(error.getDefaultMessage())
                    .append(";");
            }
            throw new MeasurementNotCreatedException(errorMsg.toString());
        }
        if (sensorService.findByName(measurement.getSensor().getName()) == null) {
            throw new MeasurementNotCreatedException("This sensor not found. Try register sensor first");
        }

        System.out.println(measurement);
        measurementService.save(new Measurement(
            Boolean.parseBoolean(measurement.getRaining()),
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

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementNotCreatedException e) {
        MeasurementErrorResponse response = new MeasurementErrorResponse(
            e.getMessage(),
            System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
