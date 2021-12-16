package dev.mariocares.montesanto.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Home {
    @GetMapping(value = "/")
    public String nueva(){
        return "index";
    }
}
