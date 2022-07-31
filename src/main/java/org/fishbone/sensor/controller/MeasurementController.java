package org.fishbone.sensor.controller;

import static org.fishbone.sensor.util.ErrorsUtil.returnErrors;

import javax.validation.Valid;
import org.fishbone.sensor.dto.MeasurementDto;
import org.fishbone.sensor.model.Measurement;
import org.fishbone.sensor.model.MeasurementResponse;
import org.fishbone.sensor.service.MeasurementService;
import org.fishbone.sensor.service.SensorService;
import org.fishbone.sensor.util.MeasurementErrorResponse;
import org.fishbone.sensor.util.MeasurementException;
import org.fishbone.sensor.util.MeasurementValidator;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    ModelMapper modelMapper;
    MeasurementValidator measurementValidator;

    public MeasurementController(MeasurementService measurementService, SensorService sensorService,
                                 ModelMapper modelMapper, MeasurementValidator measurementValidator) {
        this.measurementService = measurementService;
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
        this.modelMapper.typeMap(MeasurementDto.class, Measurement.class).addMapping(MeasurementDto::getValue,
            Measurement::setTemperature);
        this.measurementValidator = measurementValidator;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid MeasurementDto measurementDto,
                                          BindingResult bindingResult) {
        Measurement measurementToAdd = dtoToMeasurement(measurementDto);

        measurementValidator.validate(measurementToAdd, bindingResult);

        if (bindingResult.hasErrors()) {
            returnErrors(bindingResult);
        }

        measurementService.save(measurementToAdd);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping
    public MeasurementResponse getAll() {
        return new MeasurementResponse(measurementService.findAll());
    }

    @GetMapping("/rainyDaysCount")
    public long getRainyDays() {
        return measurementService.findAll()
            .stream()
            .filter(Measurement::isRaining)
            .count();
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementException e) {
        MeasurementErrorResponse response = new MeasurementErrorResponse(
            e.getMessage(),
            System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Measurement dtoToMeasurement(MeasurementDto dto) {
        return modelMapper.map(dto, Measurement.class);
    }
}
