package com.green.nowon.controller.weather;

import lombok.Data;

@Data
public class Item {
	
	private String baseDate;
	private String baseTime;
	private String category;
	private String fcstDate; //예보일자
	private String fcstTime; //예보시간
	private String fcstValue; 
	private int nx;
	private int ny;
	
	//T1H : 기온 섭씨
	//RN1 1시간 강수량 mm
}
