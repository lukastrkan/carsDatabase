package com.example.cars.controller;

import com.example.cars.model.Driver;
import com.example.cars.service.DriverService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/drivers")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping({"", "/"})
    public String list(Model model) {
        model.addAttribute("drivers", driverService.getAllDrivers());
        return "driver_list";
    }

    @GetMapping("/detail/{index}")
    public String detail(Model model, @PathVariable int index) {
        if (index < 0 || index >= driverService.getAllDrivers().size()) {
            return "redirect:/";
        }

        model.addAttribute("driver", driverService.getAllDrivers().get(index));
        return "driver_detail";
    }

    @GetMapping("/delete/{index}")
    public String delete(@PathVariable int index) {
        if (index < 0 || index >= driverService.getAllDrivers().size()) {
            return "redirect:/";
        }

        driverService.getAllDrivers().remove(index);
        return "redirect:/";
    }

    @GetMapping("/create")
    public String add(Model model) {
        model.addAttribute("driver", new Driver());
        model.addAttribute("edit", false);
        return "driver_edit";
    }

    @GetMapping("/edit/{index}")
    public String edit(Model model, @PathVariable int index) {
        if (index < 0 || index >= driverService.getAllDrivers().size()) {
            return "redirect:/";
        }
        var driver = driverService.getDriverById(index);
        driver.setId(index);
        model.addAttribute("driver", driver);
        model.addAttribute("edit", true);
        return "driver_edit";
    }

    @PostMapping("/save")
    public String save(@Valid Driver driver, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("edit", driver.getId() > -1);
            return "driver_edit";
        }

//        if (driver.getLicensePlate() == null || driver.getLicensePlate().isEmpty()) {
//            return "redirect:/";
//        }

        if (driver.getId() > -1) {
            driverService.saveDriver(driver);
            return "redirect:/";
        }

        driverService.saveDriver(driver);
        return "redirect:/";
    }
}
