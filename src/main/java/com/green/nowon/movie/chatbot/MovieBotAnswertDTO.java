package com.green.nowon.movie.chatbot;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieBotAnswertDTO {
	
	private String today;
	private String time;
	private String scenario;
	private String repNationCd;
	private String movieName;
	private int order;
	
	@Builder.Default
	private String info="안녕하세요. CODE-BOX 무비안내봇입니다.</br>안내에 따라 입력 또는 선택해 주세요.";

}
