package org.fishbone.sensor.service;

import org.fishbone.sensor.model.Sensor;
import org.fishbone.sensor.repository.SensorRepository;
import org.fishbone.sensor.util.SensorDuplicateException;
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
        if (sensorRepository.findByName(sensor.getName()) != null) {
            throw new SensorDuplicateException();
        } else {
            sensorRepository.save(sensor);
        }
    }
}
