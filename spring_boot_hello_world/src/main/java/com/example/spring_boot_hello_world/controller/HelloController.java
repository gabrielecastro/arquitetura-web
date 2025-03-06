package com.example.spring_boot_hello_world.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @GetMapping("/test")
  public String helloWorld() {
    return "Hello World!";
  }

}
