package org.fishbone.sensor.controller;

import static org.fishbone.sensor.util.ErrorsUtil.returnErrors;

import javax.validation.Valid;
import org.fishbone.sensor.dto.SensorDto;
import org.fishbone.sensor.model.Sensor;
import org.fishbone.sensor.service.SensorService;
import org.fishbone.sensor.util.MeasurementErrorResponse;
import org.fishbone.sensor.util.MeasurementException;
import org.fishbone.sensor.util.SensorValidator;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sensor")
public class SensorController {

    SensorService sensorService;
    SensorValidator sensorValidator;
    ModelMapper modelMapper;

    public SensorController(SensorService sensorService, SensorValidator sensorValidator, ModelMapper modelMapper) {
        this.sensorService = sensorService;
        this.sensorValidator = sensorValidator;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registration(@RequestBody @Valid SensorDto sensorDto,
                                                   BindingResult bindingResult) {
        Sensor sensorToAdd = dtoToSensor(sensorDto);
        sensorValidator.validate(sensorToAdd, bindingResult);

        if (bindingResult.hasErrors()) {
            returnErrors(bindingResult);
        }

        sensorService.save(sensorToAdd);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementException e) {
        MeasurementErrorResponse response = new MeasurementErrorResponse(
            e.getMessage(),
            System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Sensor dtoToSensor(SensorDto dto) {
        return modelMapper.map(dto, Sensor.class);
    }
}
