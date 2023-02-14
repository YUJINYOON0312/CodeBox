package com.green.nowon.controller.weather;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FcstTime {

	private String category;// 자료구분코드

	private String fcstValue; // 예보지점 Y 좌표

}
