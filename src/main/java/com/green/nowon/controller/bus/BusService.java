package com.green.nowon.controller.bus;

import org.springframework.web.servlet.ModelAndView;

public interface BusService {

	void getBusPath(String strSrch, ModelAndView mv);

}
