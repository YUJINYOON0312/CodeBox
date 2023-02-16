package com.green.nowon.bus;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.green.nowon.bus.dto.ArriveRequestDTO;
import com.green.nowon.bus.dto.BusArriveItem;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BusController {

	private final BusService busService;

	// 그냥 페이지 이동
	// @ResponseBody
	@GetMapping("/bus")
	public ModelAndView bus() {
		return new ModelAndView("bus/bus-index");
	}

	@GetMapping("/bus/arriveInfo")
	public List<BusArriveItem> arriveInfo(ArriveRequestDTO dto) {
		return busService.arriveInfo(dto);
	}

	private <T> String ObjectToString(T target) {
		String result = null;
		if (target instanceof Number)
			result = target.toString();
		else if (target instanceof String) {
			result = (String) target;
			result = result.replace(",", ",\n");
			result = result.replace("{", "{\n ");
			result = result.replace("(", "(\n ");
			result = result.replace("}", "\n}");
			result = result.replace(")", "\n)");
		} else {
			System.out.println("문자열도 숫자도아님");
			result = ObjectToString(target.toString());
		}

		return result;

	}

	// ajax 요청
	@GetMapping("/bus/search")
	public ModelAndView busSearch(String strSrch, ModelAndView mv) {
		mv.setViewName("bus/list");
		busService.getBusPath(strSrch, mv);
		return mv;
	}

	@GetMapping("/bus/{busRouteId}")
	public ModelAndView getStaionsByRouteList(@PathVariable String busRouteId, ModelAndView mv) {
		mv.setViewName("bus/station-list");
		busService.getStaionsByRouteList(busRouteId, mv);
		return mv;
	}

	@GetMapping("/placesSearch")
	public ModelAndView placesSearch(String search, ModelAndView mv) {
		mv.setViewName("bus/list");
		busService.getPlacesSearch(search, mv);
		return mv;
	}

}
