package com.green.nowon.weather;

import java.util.ArrayList;
import java.util.List;

import com.green.nowon.controller.weather.FcstDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {

	String fcstDate;

	@Builder.Default
	List<FcstDate> fcstDates = new ArrayList<>();

	public ItemDTO add(final FcstDate data) {
		add(data);
		return this;
	}

}
