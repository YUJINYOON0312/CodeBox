package com.green.nowon.controller.api.movie;

import java.util.List;

public interface MovieService {

	List<Item> dailyBoxOfficeList(String targetDt);

}
