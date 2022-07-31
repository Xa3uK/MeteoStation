package org.fishbone.sensor.service;

import java.time.LocalDateTime;
import java.util.List;
import org.fishbone.sensor.dto.MeasurementDto;
import org.fishbone.sensor.model.Measurement;
import org.fishbone.sensor.repository.MeasurementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MeasurementService {

    MeasurementRepository measurementRepository;
    SensorService sensorService;

    public MeasurementService(MeasurementRepository measurementRepository, SensorService sensorService) {
        this.measurementRepository = measurementRepository;
        this.sensorService = sensorService;
    }

    @Transactional
    public void save(Measurement measurement) {
        measurement.setSensor(sensorService.findByName(measurement.getSensor().getName()));
        measurement.setCreatedAt(LocalDateTime.now());
        measurementRepository.save(measurement);
    }

    public List<Measurement> findAll() {
        return measurementRepository.findAll();
    }
}
