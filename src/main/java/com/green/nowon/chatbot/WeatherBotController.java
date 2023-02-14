package com.green.nowon.chatbot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WeatherBotController {

	@Autowired
	private WeatherBotService service;

	@PostMapping("/weather-bot")
	public String message(final String message, final Model model) throws Exception {

		model.addAttribute("msg", service.getAnswer(message));

		return "chatbot/bot-message-weather";
	}

}
