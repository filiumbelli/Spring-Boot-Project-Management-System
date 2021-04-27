package com.sofisticat.management.controllers;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Value("${version}")
    public String version;

    @GetMapping("/")
    public String displayHome(Model model) {
        model.addAttribute("version", version);
        return "main/home";
    }


}
