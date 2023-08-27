package com.example.RestAPI.services;

import com.example.RestAPI.exceptions.SensorNotFoundException;
import com.example.RestAPI.models.Sensor;
import com.example.RestAPI.repositories.SensorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorsService {

    private final SensorsRepository sensorsRepository;

    @Autowired
    public SensorsService(SensorsRepository sensorsRepository) {
        this.sensorsRepository = sensorsRepository;
    }
    @Transactional
    public void save(Sensor sensor){
        sensorsRepository.save(sensor);
    }

    public Optional<Sensor> findByName(String name){
        return sensorsRepository.findByName(name);
    }

    public Sensor findByNameOrThrowException(String name){
        Optional<Sensor> sensor = sensorsRepository.findByName(name);
        return sensor.orElseThrow(SensorNotFoundException::new);
    }
}
