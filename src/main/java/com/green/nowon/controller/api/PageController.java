package com.green.nowon.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

	@GetMapping("/api/weather")
	public String weather() {
		return "";
	}

	@GetMapping("/api/movie")
	public String movie() {
		return "api/movie/default";
	}

	@GetMapping("/api/bus")
	public String bus() {
		return "api/bus/bus-index";
	}

}
