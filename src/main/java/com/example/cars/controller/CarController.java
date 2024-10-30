package com.example.cars.controller;

import com.example.cars.model.Car;
import com.example.cars.service.CarService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping({"/", ""})
    public String list(Model model) {
        model.addAttribute("cars", carService.getAllCars());
        return "car_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable long id) {
        if (id < 0 || id >= carService.getAllCars().size()) {
            return "redirect:/";
        }

        model.addAttribute("car", carService.getCarById(id));
        return "car_detail";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        if (id < 0 || id >= carService.getAllCars().size()) {
            return "redirect:/";
        }

        carService.deleteCarById(id);
        return "redirect:/";
    }

    @GetMapping("/create")
    public String add(Model model) {
        model.addAttribute("car", new Car());
        model.addAttribute("edit", false);
        return "car_edit";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable long id) {
        if (id < 0 || id >= carService.getAllCars().size()) {
            return "redirect:/";
        }
        var car = carService.getCarById(id);
        model.addAttribute("car", car);
        model.addAttribute("edit", true);
        return "car_edit";
    }

    @PostMapping("/save")
    public String save(@Valid Car car, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("edit", car.getId() > -1);
            return "car_edit";
        }

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
