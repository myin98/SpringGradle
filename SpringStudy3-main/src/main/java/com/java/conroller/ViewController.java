package com.java.conroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.mapper.Temp1Mapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/v1")
@RequiredArgsConstructor
public class ViewController {
	
	private final Temp1Mapper tm;
	
	@GetMapping(value = {"/findList", "/findList/{accept:[0-1]}"})
	public String findList(@PathVariable(name = "accept", required = false) String accept, Model model) {
		model.addAttribute("list", tm.findList(accept));
		return "v1/list";
	}
	
	@GetMapping("/new")
	public String newPage() {
		return "v1/new";
	}
	
	@PostMapping("/save")
	public String save(@RequestParam Map map) {
		log.info("Map : {}", map);
		tm.save(map);
		return "redirect:/v1/detail/" + map.get("no"); 
	}
	
	@GetMapping(value = {"/detail/{no:[0-9]+}", "/detail/{no:[0-9]+}/{accept:[0-1]}"})
	public String detail(@PathVariable(name = "no") int no, @PathVariable(name = "accept", required = false) String accept, Model model) {
		log.info("no : {}", no);
		if(accept != null) {
			Map map = new HashMap<>();
			map.put("no", no);
			map.put("accept", accept);
			tm.accept(map);
		}
		model.addAttribute("map", tm.findOne(no));
		return "v1/detail";
	}
	
	@PostMapping("/edit/{no:[0-9+]}")
	public String edit(@PathVariable(name = "no") int no, @RequestParam Map map) {
		log.info("no : {}", no);
		map.put("no", no);
		tm.edit(map);
		return "redirect:/v1/detail/" + no; 
	}

}
