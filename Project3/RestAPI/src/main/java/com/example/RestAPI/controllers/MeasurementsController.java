package com.example.RestAPI.controllers;

import com.example.RestAPI.dto.MeasurementDTO;
import com.example.RestAPI.exceptions.ErrorResponse;
import com.example.RestAPI.exceptions.MeasurementNotCreatedException;
import com.example.RestAPI.exceptions.SensorNotFoundException;
import com.example.RestAPI.models.Measurement;
import com.example.RestAPI.services.MeasurementsService;
import com.example.RestAPI.services.SensorsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {

    private final ModelMapper modelMapper;
    private final MeasurementsService measurementsService;
    private final SensorsService sensorsService;

    @Autowired
    public MeasurementsController(ModelMapper modelMapper, MeasurementsService measurementsService,SensorsService sensorsService) {
        this.modelMapper = modelMapper;
        this.measurementsService = measurementsService;
        this.sensorsService = sensorsService;
    }
    //Methods
//||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurements(@RequestBody @Valid MeasurementDTO measurementDTO,
                                                      BindingResult result){
        if(result.hasErrors()){
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = result.getFieldErrors();

            for(FieldError error : errors){
                errorMsg.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append("; ");
            }
            throw new MeasurementNotCreatedException(errorMsg.toString());
        }

        Measurement measurement = convertToMeasurement(measurementDTO);
        measurement.setSensor(sensorsService.findByNameOrThrowException(measurement.getSensor().getName()));

        measurementsService.save(measurement);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping()
    public List<MeasurementDTO> getMeasurement(){
        return measurementsService.findAll().stream().map(this::convertToMeasurementDTO).collect(Collectors.toList());
    }
    @GetMapping("/rainyDaysCount")
    public Long getRainyDaysCount(){
        return measurementsService.rainyDaysCount();
    }



    //Handlers
//||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(MeasurementNotCreatedException e){
        ErrorResponse response = new ErrorResponse(e.getMessage(),System.currentTimeMillis());

        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(SensorNotFoundException e){
        ErrorResponse response = new ErrorResponse("Сенсор с таким именем не был найден",System.currentTimeMillis());

        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }


    //Converters
//||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||


    public MeasurementDTO convertToMeasurementDTO(Measurement measurement){
        return modelMapper.map(measurement, MeasurementDTO.class);
    }


    public Measurement convertToMeasurement(MeasurementDTO measurementDTO){
        return modelMapper.map(measurementDTO, Measurement.class);
    }
}
