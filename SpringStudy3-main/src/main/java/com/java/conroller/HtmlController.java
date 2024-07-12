package com.java.conroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/html")
public class HtmlController {

	@GetMapping("/{page:[0-9]+}")
	public String menu1(@PathVariable("page") int page, Model model) {
		log.info("page : {}", page);
		List<String[]> list = new ArrayList<String[]>();
		String[] menu1 = new String[] {"bi1","bi2","bi3","bi2", "bi3"};
		String[] menu2 = new String[] {"bi4","bi5","bi6"};
		String[] menu3 = new String[] {"bi7","bi8"};
		String[] menu4 = new String[] {"bi9"};
		list.add(menu1);
		list.add(menu2);
		list.add(menu3);
		list.add(menu4);		
		model.addAttribute("list", list.get(page));
		return "html/menu";
	}
	
}
