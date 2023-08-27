package com.example.RestAPI.services;

import com.example.RestAPI.models.Measurement;
import com.example.RestAPI.repositories.MeasurementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementsService {

    private final MeasurementsRepository measurementsRepository;


    @Autowired
    public MeasurementsService(MeasurementsRepository measurementsRepository) {
        this.measurementsRepository = measurementsRepository;
    }
    @Transactional
    public void save(Measurement measurement){
        measurementsRepository.save(measurement);
    }

    public List<Measurement> findAll(){
        return measurementsRepository.findAll();
    }

    public Long rainyDaysCount(){
        return measurementsRepository.findAll().stream().filter(Measurement::getRaining).count();
    }


}
