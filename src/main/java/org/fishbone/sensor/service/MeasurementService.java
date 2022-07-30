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

    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    @Transactional
    public void save(MeasurementDto measurement) {

        measurementRepository.save(new Measurement(
            Boolean.parseBoolean(measurement.getRaining()),
            measurement.getValue(),
            measurement.getSensor().getName(),
            LocalDateTime.now()));
    }

    public List<Measurement> findAll() {
        return measurementRepository.findAll();
    }
}
