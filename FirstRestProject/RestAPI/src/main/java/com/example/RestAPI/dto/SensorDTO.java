package com.example.RestAPI.dto;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SensorDTO {
    @NotNull
    @Size(min = 3, max = 30, message = "Имя сенсора должно содержать от 3 до 30 символов")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
