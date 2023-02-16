package com.green.nowon.controller.weather;

import lombok.Data;

@Data
public class Item {

	private String baseDate;

	private String baseTime;

	private String category;// 자료구분코드

	private String fcstDate;// 예보일자

	private String fcstTime;// 예보시간

	private String fcstValue; // 예보지점 Y 좌표

	private int nx; // 예보지점 X 좌표

	private int ny; // 예보지점 Y 좌표

	// POP 강수확률 %
	// PTY 강수형태 코드값
	// PCP 1시간 강수량 범주 (1 mm)
	// REH 습도 %
	// SNO 1시간 신적설 범주(1 cm)
	// SKY 하늘상태 코드값
	// TMP 1시간 기온 ℃
	// TMN 일 최저기온 ℃
	// TMX 일 최고기온 ℃
	// UUU 풍속(동서성분) m/s
	// VVV 풍속(남북성분) m/s
	// WAV 파고 M
	// VEC 풍향 deg
	// WSD 풍속 m/s

}
