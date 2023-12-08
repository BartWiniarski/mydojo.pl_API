package me.winiarski.mydojo_pl.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller

public class HomeController {

    @GetMapping("/")
    @ResponseBody
    public String home(){

        return "I'm up and running!";
    }
}
