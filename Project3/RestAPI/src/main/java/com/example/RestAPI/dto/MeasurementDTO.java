package com.example.RestAPI.dto;

import javax.persistence.Column;
import javax.validation.constraints.*;

public class MeasurementDTO {

    @Column(name = "value")
    @NotNull(message = "Значение температуры не должно быть пустым")
    @Min(value = -100, message = "Значение температуры должно быть выше -100 градусов")
    @Max(value = 100, message = "значение температуры должно не превышать 100 градусов")
    private Double value;


    @Column(name = "raining")
    @NotNull(message = "Дождливость не должна быть пустой")
    private Boolean raining;

    @NotNull(message = "Нужно указать сенсор")
    private SensorDTO sensor;

    public Double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Boolean getRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}
