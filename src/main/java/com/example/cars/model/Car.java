package com.example.cars.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Car {
    private int id = -1;
    @Size(min = 7, max = 7, message = "License plate must be 7 characters long")
    private String licensePlate;
    @NotBlank
    private String color;
    @Min(30)
    @Max(100)
    private float tankVolume;
    @Min(2)
    @Max(10)
    private int numberOfSeats;

    public Car(){}

    public Car(String licensePlate, String color, float tankVolume, int numberOfSeats) {
        this.licensePlate = licensePlate;
        this.color = color;
        this.tankVolume = tankVolume;
        this.numberOfSeats = numberOfSeats;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public float getTankVolume() {
        return tankVolume;
    }

    public void setTankVolume(float tankVolume) {
        this.tankVolume = tankVolume;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

