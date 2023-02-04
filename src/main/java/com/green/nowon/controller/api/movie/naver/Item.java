package com.green.nowon.controller.api.movie.naver;

import lombok.Data;

@Data
public class Item {
	
	private String title;
	private String link;
	private String image;
	private String subtitle;
	private String pubDate;
	private String director;
	private String actor;
	private String userRating;	
}
