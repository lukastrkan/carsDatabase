package com.example.cars.service;

import com.example.cars.model.Driver;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface DriverService {
    List<Driver> getAllDrivers();

    Driver getDriverById(long id);

    void deleteDriverById(long id);

    void saveDriver(Driver driver);
}
