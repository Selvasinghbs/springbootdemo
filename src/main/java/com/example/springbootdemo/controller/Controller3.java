package com.example.springbootdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller3 {
	
	@GetMapping("/sample")
	public String sample() {
		return "Java Developers";
	}
}