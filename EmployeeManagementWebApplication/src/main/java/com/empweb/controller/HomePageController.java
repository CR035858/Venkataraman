package com.empweb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomePageController {
	
	@GetMapping("/")
	public String home() {
		return "<H1>Welcome to the root URL!</H1>";
	}

}
