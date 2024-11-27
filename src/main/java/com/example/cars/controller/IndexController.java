package com.example.cars.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {
    @GetMapping({"", "/", "/index"})
    public String index() {
        return "index";
    }

    @GetMapping("/403")
    @ResponseBody
    public String accessDenied() {
        return "403";
    }

    @GetMapping("/admin")
    @ResponseBody
    public String accessDeniedAdmin() {
        return "403 admin";
    }

    @GetMapping("/blog")
    @ResponseBody
    public String blog() {
        return "blog";
    }
}
