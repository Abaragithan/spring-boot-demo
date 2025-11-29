package com.example.spring.boot.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@ResponseBody
public class HomeController {

    @GetMapping
    public String getHome() {
        return "Welcome to Home page";
    }

    @GetMapping("/dashboard")
    public String getDashboard() {
        return "Welcome to Dashboard";
    }
}
