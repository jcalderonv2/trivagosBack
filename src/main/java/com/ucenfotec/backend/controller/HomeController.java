package com.ucenfotec.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String greeting() {
		return "redirect:/swagger-ui.html";
	}
}