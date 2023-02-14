package com.green.nowon.chatbot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WeatherBotService {

  private String[] senario = { "1.조회할 지역을 입력해주세요.(ex:서울시 )", "2. 조회할 날짜 입력해주세요", "조회할 시간을 입력해주세요 (ex: 오후2시 14:00)" };

  public WeatherAnswerDTO getAnswer(String message) {
    LocalDateTime today = LocalDateTime.now();
    final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("a H:mm");
    final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");

    WeatherAnswerDTO answer = WeatherAnswerDTO.builder().time(timeFormatter.format(today)).build();
    if ("안녕".equals(message)) {
      answer.setInfo("날씨이용감사");
      answer.setToday(dateFormatter.format(today));

    }
    return answer;
  }

}
