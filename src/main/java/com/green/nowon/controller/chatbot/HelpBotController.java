package com.green.nowon.controller.chatbot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.green.nowon.service.impl.KomoranService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class HelpBotController {

	private final KomoranService komoranService;

	@PostMapping("/hbot")
	public String message(String message, Model model) throws Exception {

		model.addAttribute("msg", komoranService.nlpAnalyze(message));

		return "chatbot/bot-message";
	}

}