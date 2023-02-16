package com.green.nowon.movie.chatbot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MovieBotController {

	@Autowired
	private MovieBotService service;
	
	@PostMapping("/moviebot/{order}")
	public ModelAndView moiveBotScenario(MovieBotRequestDTO dto, ModelAndView mv) {
		service.movieBotScenario(dto, mv);
		mv.setViewName("chatbot/bot-message-movie");
		return mv;
	}
	
	@GetMapping("/moviebot/dailyBoxOffice")
	public ModelAndView dailyBoxOffice(String repNationCd, ModelAndView mv) {
		service.botDailyBoxOffice(repNationCd, mv);
		mv.setViewName("chatbot/movie-name-list");
		return mv;
	}
	
	@GetMapping("/moviebot/search/naverApi")
	public ModelAndView searchNaverApi(String movieName, ModelAndView mv) {
		service.searchNaverApi(movieName, mv);
		mv.setViewName("chatbot/movie-detail");
		return mv;
	}
}
