package com.example.cars.service;

import com.example.cars.model.Driver;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DriverServiceImpl implements DriverService {
    ArrayList<Driver> drivers = new ArrayList<>();

    @Override
    public ArrayList<Driver> getAllDrivers() {
        return drivers;
    }

    @Override
    public Driver getDriverById(int id) {
        for (Driver driver : drivers) {
            if (driver.getId() == id) {
                return driver;
            }
        }
        return null;
    }

    @Override
    public void deleteDriverById(int id) {
        drivers.removeIf(driver -> driver.getId() == id);
    }

    @Override
    public void saveDriver(Driver driver) {
        if (driver.getId() != -1) {
            drivers.removeIf(c -> c.getId() == driver.getId());
        } else if (driver.getId() == -1) {
            driver.setId(drivers.size());
        }
        drivers.add(driver);
    }
}
