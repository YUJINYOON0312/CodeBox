package com.green.nowon.domain.dto.board;

import com.green.nowon.domain.entity.board.BoardImgEntity;

import lombok.Getter;

@Getter
public class BoardImgDTO {

	private long fno;

	private String orgName;

	private String newName;

	private String url;

	private boolean defImg;

	// 편의필드
	private String imgUrl;

	private String orgImgUrl;

	public BoardImgDTO(BoardImgEntity e) {
		fno = e.getFno();
		orgName = e.getOrgName();
		newName = e.getNewName();
		url = e.getUrl();
		defImg = e.isDef();

		imgUrl = url + newName;
		orgImgUrl = url + orgName;
	}

}
