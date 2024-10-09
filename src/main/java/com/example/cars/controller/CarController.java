package com.example.cars.controller;

import com.example.cars.model.Car;
import com.example.cars.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("cars", carService.getAllCars());
        return "list";
    }

    @GetMapping("/detail/{index}")
    public String detail(Model model, @PathVariable int index) {
        if (index < 0 || index >= carService.getAllCars().size()) {
            return "redirect:/";
        }

        model.addAttribute("car", carService.getAllCars().get(index));
        return "detail";
    }

    @GetMapping("/delete/{index}")
    public String delete(@PathVariable int index) {
        if (index < 0 || index >= carService.getAllCars().size()) {
            return "redirect:/";
        }

        carService.getAllCars().remove(index);
        return "redirect:/";
    }

    @GetMapping("/create")
    public String add(Model model) {
        model.addAttribute("car", new Car());
        model.addAttribute("edit", false);
        return "edit";
    }

    @GetMapping("/edit/{index}")
    public String edit(Model model, @PathVariable int index) {
        if (index < 0 || index >= carService.getAllCars().size()) {
            return "redirect:/";
        }
        var car = carService.getCarById(index);
        car.setId(index);
        model.addAttribute("car", car);
        model.addAttribute("edit", true);
        return "edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Car car) {
        if (car.getLicensePlate() == null || car.getLicensePlate().isEmpty()) {
            return "redirect:/";
        }

        if (car.getId() > -1) {
            carService.saveCar(car);
            return "redirect:/";
        }

        carService.saveCar(car);
        return "redirect:/";
    }
}
