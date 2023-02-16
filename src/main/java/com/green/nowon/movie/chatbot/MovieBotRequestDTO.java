package com.green.nowon.movie.chatbot;

import lombok.Data;

@Data
public class MovieBotRequestDTO {
	
	private String message;
	private int order;
	private String repNationCd;
	private String movieName;

}
