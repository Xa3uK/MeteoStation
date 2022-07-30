package org.fishbone.sensor.service;

import org.fishbone.sensor.model.Measurement;
import org.fishbone.sensor.repository.MeasurementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MeasurementService {
    MeasurementRepository measurementRepository;

    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    @Transactional
    public void save(Measurement measurement){
        measurementRepository.save(measurement);
    }
}
