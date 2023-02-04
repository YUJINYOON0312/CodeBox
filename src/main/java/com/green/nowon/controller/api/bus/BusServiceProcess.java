package com.green.nowon.controller.api.bus;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.nowon.controller.api.dto.BusRouteListResponse;
import com.green.nowon.controller.api.openapi.OpenApiUtil;

@Service
public class BusServiceProcess implements BusService{

	//DB가 아니고 openAPI
	
	@Value("${data.go.kr.bus.serviceKey}")
	private String serviceKey;
	
	@Override
	public void getBusPath(String strSrch, ModelAndView mv) {
		
		StringBuilder urlBuilder = new StringBuilder("http://ws.bus.go.kr/api/rest/busRouteInfo/getBusRouteList"); /*URL*/
        
        try {
        	urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "="+serviceKey); /*Service Key*/
			urlBuilder.append("&" + URLEncoder.encode("strSrch","UTF-8") + "=" + URLEncoder.encode(strSrch, "UTF-8"));/*검색할 노선번호*/
			urlBuilder.append("&" + URLEncoder.encode("resultType","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));/*[xml,json]*/
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
        
        String apiURL=urlBuilder.toString();
        //System.out.println(apiURL);
        Map<String, String> requestHeaders=new HashMap<>();
        requestHeaders.put("Content-type", "application/json");
        
        String responseData=OpenApiUtil.request(apiURL, requestHeaders, "GET");
        
        //json->calss Mapping 해줌
        ObjectMapper objectMapper=new ObjectMapper();
        try {
        	BusRouteListResponse result=objectMapper.readValue(responseData, BusRouteListResponse.class);
        	
        	//System.out.println(result);
        	mv.addObject("list", result.getMsgBody().getItemList());
//         						.stream()
//        						.filter(item->item.getBusRouteAbrv().equals(strSrch) && item.getRouteType().equals("1"))
//        						.toList().forEach(i->{
//        							System.out.println(">>>>:"+i);
//        						});
        	
        	
        	
        	
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
        
        System.out.println(responseData);
        
		
	}

}
