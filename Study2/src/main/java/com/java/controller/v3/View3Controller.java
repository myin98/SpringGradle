package com.java.controller.v3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v3")
public class View3Controller {

	@GetMapping("/")
	public String v3() {
		return "v3/page";
	}

}