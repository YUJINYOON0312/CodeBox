package com.green.nowon.controller.api.bus;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import net.bytebuddy.asm.Advice.Local;


@RestController
public class BusController {
	
	@Autowired
	private BusService service;
	
	
	//그냥페이지이동
	//@ResponseBody
	@GetMapping("/bus")
	public ModelAndView bus() {
		return new ModelAndView("bus/bus-index") ;
	}
	
	//ajax 요청
	@GetMapping("/api/bus/search")
	public ModelAndView busSearch(String strSrch, ModelAndView mv) {
		mv.setViewName("api/bus/list");
		service.getBusPath(strSrch, mv);
		return mv;
	}

}
