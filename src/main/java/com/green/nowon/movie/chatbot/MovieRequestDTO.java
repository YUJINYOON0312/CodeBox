package com.green.nowon.movie.chatbot;

import lombok.Data;

@Data
public class MovieRequestDTO {
	
	private String message;
	private String areaInfo;
	private String nx;
	private String ny;
	private String fcstDate;
	private String fcstTime;

}
