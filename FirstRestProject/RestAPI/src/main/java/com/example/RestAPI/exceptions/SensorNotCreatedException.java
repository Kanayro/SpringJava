package com.example.RestAPI.exceptions;

public class SensorNotCreatedException extends RuntimeException{

    public SensorNotCreatedException(String msg){
        super(msg);
    }
}
