package com.wipro.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
@RequestMapping("/admin")
public class AdminController {
	
	@GetMapping("/hello")
    public String sayHello ()
    { return "welcome to admin dashboard" ;}
}
