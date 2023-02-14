package com.green.nowon.chatbot;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class WeatherAnswerDTO {

  private String info;

  private String scenario;

  private String today;

  private String time;

}
