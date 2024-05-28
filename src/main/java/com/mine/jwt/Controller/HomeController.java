package com.mine.jwt.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class HomeController {

    @GetMapping(value = "/")
    public String home() {
        return "You did reach home";
    }

    @GetMapping(value = "/adm")
    public String homeAdm() {
        return "You did reach home as ADM";
    }
}



