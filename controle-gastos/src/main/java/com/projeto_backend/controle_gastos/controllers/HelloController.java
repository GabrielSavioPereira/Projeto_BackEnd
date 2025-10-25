package com.projeto_backend.controle_gastos.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/")
    String hello(){
        return "Hello world";
    }
}
