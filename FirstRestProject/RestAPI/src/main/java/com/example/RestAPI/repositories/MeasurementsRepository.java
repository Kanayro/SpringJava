package com.example.RestAPI.repositories;

import com.example.RestAPI.models.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementsRepository extends JpaRepository<Measurement,Integer> {
}
