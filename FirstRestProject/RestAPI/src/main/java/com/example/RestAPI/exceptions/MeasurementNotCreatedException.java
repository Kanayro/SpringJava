package com.example.RestAPI.exceptions;

public class MeasurementNotCreatedException extends RuntimeException{

    public MeasurementNotCreatedException(String msg){
        super(msg);
    }
}
