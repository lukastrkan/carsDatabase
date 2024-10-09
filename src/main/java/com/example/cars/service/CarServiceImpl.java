package com.example.cars.service;

import com.example.cars.model.Car;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    ArrayList<Car> cars = new ArrayList<>();

    @Override
    public ArrayList<Car> getAllCars() {
        return cars;
    }

    @Override
    public Car getCarById(int id) {
        for (Car car : cars) {
            if (car.getId() == id) {
                return car;
            }
        }
        return null;
    }

    @Override
    public void deleteCarById(int id) {
        cars.removeIf(car -> car.getId() == id);
    }

    @Override
    public void saveCar(Car car) {
        if (car.getId() != -1) {
            cars.removeIf(c -> c.getId() == car.getId());
        } else if (car.getId() == -1) {
            car.setId(cars.size());
        }
        cars.add(car);
    }
}
