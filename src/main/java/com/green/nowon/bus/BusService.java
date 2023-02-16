package com.green.nowon.bus;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.green.nowon.bus.dto.ArriveRequestDTO;
import com.green.nowon.bus.dto.BusArriveItem;

public interface BusService {

	void getBusPath(String strSrch, ModelAndView mv);

	void getStaionsByRouteList(String busRouteId, ModelAndView mv);

	void getPlacesSearch(String search, ModelAndView mv);

	List<BusArriveItem> arriveInfo(ArriveRequestDTO dto);

}
