package com.green.nowon.controller.weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.nowon.openapi.OpenApiUtil;

@RestController // controller + @responsebody !!!!
public class WeatherController {

	// SPA 싱글페이지

	@Value("${data.go.kr.weather.serviceKey}")
	private String serviceKey;

	@GetMapping("/weather")
	public ModelAndView weather() {
		return new ModelAndView("weather/default");
	}

	//Itwm -> success function의 result : 자동으로 JSON으로 파싱해서
	@GetMapping("/weather/info") // 날씨 default의 ajax
	public ModelAndView weatherInfo(String nx, String ny, String baseDate, String baseTime, ModelAndView mv) throws IOException {

		String serviceUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst";
		StringBuilder urlBuilder = new StringBuilder(serviceUrl);// /URL/

		try {
			urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" +serviceKey); //Service Key
			urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("dataType", "UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8"));
			urlBuilder
					.append("&" + URLEncoder.encode("base_date", "UTF-8") + "=" + URLEncoder.encode(baseDate, "UTF-8"));
			urlBuilder
					.append("&" + URLEncoder.encode("base_time", "UTF-8") + "=" + URLEncoder.encode(baseTime, "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("nx", "UTF-8") + "=" + URLEncoder.encode(nx, "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("ny", "UTF-8") + "=" + URLEncoder.encode(ny, "UTF-8"));

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		
		  Map<String, String> requestHeaders = new HashMap<>();
		 requestHeaders.put("Content-type", "application/json");
		  
		 String responseStr = OpenApiUtil.request(urlBuilder.toString(), null, "GET");
		 System.out.println(responseStr);
		 
		 ObjectMapper om=new ObjectMapper();
		 WeatherResult result=om.readValue(responseStr, WeatherResult.class);
		 
		 List<Item> list=result.getResponse().getBody().getItems().getItem().stream()
				 //return이 true collect 하고 false면 필터링처리
				 .filter(i->i.getCategory().equals("POP") || i.getCategory().equals("SKY") || i.getCategory().equals("TMP"))
				 .collect(Collectors.toList())
				;

		//System.out.println(list.);
		 
		 mv.addObject("list",list);
		 mv.setViewName("weather/list");
		 
		return mv; 

	}
}
