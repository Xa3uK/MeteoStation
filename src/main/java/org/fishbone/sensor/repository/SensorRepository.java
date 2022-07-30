package org.fishbone.sensor.repository;

import org.fishbone.sensor.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer> {

    Sensor findByName(String name);
}
