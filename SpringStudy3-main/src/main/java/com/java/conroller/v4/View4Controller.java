package com.java.conroller.v4;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/v4")
public class View4Controller {

	@GetMapping("/findList")
	public String findList() {
		return "v4/list";
	}
	
	@GetMapping("/new")
	public String newPage() {
		return "v4/new";
	}
	
	@GetMapping("/detail/{no:[0-9]+}")
	public String detail(@PathVariable(name="no") int no) {
		log.info("NO : {}", no);
		return "v4/detail";
	}
	
}
