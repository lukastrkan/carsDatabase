package com.example.cars.controller;

import com.example.cars.model.Car;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CarController {
    List<Car> cars = new ArrayList<>();

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("cars", cars);
        return "list";
    }

    @GetMapping("/detail/{index}")
    public String detail(Model model, @PathVariable int index) {
        if (index < 0 || index >= cars.size()) {
            return "redirect:/";
        }

        model.addAttribute("car", cars.get(index));
        return "detail";
    }

    @GetMapping("/delete/{index}")
    public String delete(@PathVariable int index) {
        if (index < 0 || index >= cars.size()) {
            return "redirect:/";
        }

        cars.remove(index);
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
        if (index < 0 || index >= cars.size()) {
            return "redirect:/";
        }
        var car = cars.get(index);
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

        if (car.getId() > -1){
            cars.remove(car.getId());
            cars.add(car.getId(), car);
            return "redirect:/";
        }

        cars.add(car);
        return "redirect:/";
    }
}
