package org.fishbone.sensor.service;

import org.fishbone.sensor.model.Sensor;
import org.fishbone.sensor.repository.SensorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class SensorService {

    SensorRepository sensorRepository;

    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Transactional
    public void save(Sensor sensor) {
            sensorRepository.save(sensor);
    }


    public Sensor findByName(String name){
        return sensorRepository.findByName(name);
    }
}
