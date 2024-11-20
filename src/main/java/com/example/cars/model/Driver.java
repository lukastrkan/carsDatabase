package com.example.cars.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name = "drivers")
public class Driver {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String name;
    @Min(1)
    private int age;
    @Min(20000)
    private int salary;
    //@ManyToMany
    //@JoinTable(name = "driver_car", joinColumns = @JoinColumn(name = "driver_id"),
    //inverseJoinColumns = @JoinColumn(name = "car_id"))
    @OneToMany(mappedBy = "driver")
    private List<Car> cars;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public List<Car> getCars() {return cars;}
}
