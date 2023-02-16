package com.green.nowon.movie.chatbot;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.nowon.movie.MovieResponse;
import com.green.nowon.naver.NaverTokenDTO;
import com.green.nowon.naver.OrgResponse;
import com.green.nowon.naver.movie.NaverMovieResponse;
import com.green.nowon.openapi.OpenApiUtil;

import kr.or.kobis.kobisopenapi.consumer.rest.KobisOpenAPIRestService;


@Service
public class MovieBotService {

	String[] scenario = {
			"1. 국내/해외 영화를 선택해 주세요.",
			"2. 조회할 영화를 선택해 주세요.",
			"의 상세 정보입니다."
	};
	
	private final String key="11ff91e57e98e3cac398ded6bb8f8c4f";
	
	private KobisOpenAPIRestService kobis;
	public MovieBotService(){
		kobis=new KobisOpenAPIRestService(key);
	}
	
	public void botDailyBoxOffice(String repNationCd, ModelAndView mv) {
		String itemPerPage=null;
		String multiMovieYn=null;
		String wideAreaCd=null;
		
		//필수 파라미터 문자열 yyyyMMdd 전날까지 
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDate yesterday=LocalDate.now().minusDays(1);
		//System.out.println(yesterday.format(formatter));//2023-01-31 --> 20230131
		String targetDt = yesterday.format(formatter);//날짜 저장
		
		ObjectMapper objectMapper=new ObjectMapper();
		MovieResponse response=null;
		try {
			//JSON형식의 문자열데이터로 결과 리턴
			String result=kobis.getDailyBoxOffice(true, targetDt, itemPerPage, multiMovieYn, repNationCd, wideAreaCd);
			//System.out.println(result);
			//json 문자열데이터를 -> 클래스에 매핑
			response=objectMapper.readValue(result, MovieResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mv.addObject("list",response.getBoxOfficeResult().getDailyBoxOfficeList());
	}
	
	public void movieBotScenario(MovieBotRequestDTO dto, ModelAndView mv) {

		LocalDateTime today=LocalDateTime.now();
		//DateTimeFormatter dateFormatter=DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
		DateTimeFormatter timeFormatter=DateTimeFormatter.ofPattern("a H:mm");
		DateTimeFormatter dateFormatter=DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
		
		//기존 sc에 영화제목 넣기
		String sc=scenario[dto.getOrder()];
		if(dto.getOrder()==2) {
			sc= "3. [<span style='color:white'>" + dto.getMovieName() + "</span>]" + sc;
		}
		
		mv.addObject("msg", MovieBotAnswertDTO.builder()
								.today(dateFormatter.format(today))
								.time(timeFormatter.format(today))	
								.scenario(sc)
								.order(dto.getOrder()+1)
								.movieName(dto.getMovieName())
								.build());
		
		
	}

	@Value("${navar.api.movie.client-id}")
	String movieKey;
	@Value("${navar.api.movie.client-secret}")
	String movieSecret;
	
	public void searchNaverApi(String movieName, ModelAndView mv) {
		
		String serviceUrl="https://openapi.naver.com/v1/search/movie.json";
		StringBuilder urlBuilder = new StringBuilder(serviceUrl); /*URL*/
		
		try {
            urlBuilder.append("?" + URLEncoder.encode("query","UTF-8") + "=" + URLEncoder.encode(movieName, "UTF-8"));
            
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", movieKey);
        requestHeaders.put("X-Naver-Client-Secret", movieSecret);
        requestHeaders.put("Content-Type", "application/json");
        
        String responseString=OpenApiUtil.request(urlBuilder.toString(), requestHeaders, "GET");
        
        ObjectMapper objectMapper=new ObjectMapper();
        NaverMovieResponse response=null;
        try {
        	response=objectMapper.readValue(responseString, NaverMovieResponse.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
        
        mv.addObject("detail",response.getItems().get(0));
		
	}


}
