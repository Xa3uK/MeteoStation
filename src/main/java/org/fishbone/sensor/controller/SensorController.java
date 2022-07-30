package org.fishbone.sensor.controller;

import org.fishbone.sensor.model.Sensor;
import org.fishbone.sensor.service.SensorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public void registration(@RequestBody Sensor sensor) {
        System.out.println(sensor);
        sensorService.save(sensor);
    }

    @GetMapping("/test")
    public ResponseEntity test(){
        return new ResponseEntity(HttpStatus.OK);
    }
}