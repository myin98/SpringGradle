package com.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/v1")
@Controller
public class HomeController {
	
	@GetMapping("/home")
	public String home() {
		return "index";
	}
	
	@GetMapping("/list")
	public String list() {
		return "list";
	}
	
}
