package com.green.nowon.domain.dto.board;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.green.nowon.domain.entity.board.GeneralBoardEntity;

import lombok.Getter;

@Getter
public class GenBoardDetailDTO {

	private long bno;

	private String title;

	private String content;

	private int readCount;

	// private String writerName;
	private String writerId;

	private LocalDateTime createdDate;

	private LocalDateTime updatedDate;

	private LocalDate toDay;

	private long mno;

	public GenBoardDetailDTO(GeneralBoardEntity ent) {
		bno = ent.getBno();
		title = ent.getTitle();
		content = ent.getContent();
		readCount = ent.getReadCount();
		// this.writerName =ent.getMember().getName();
		// this.writerId =String.valueOf(ent.getMno());//.getId();
		createdDate = ent.getCreatedDate();
		updatedDate = ent.getUpdatedDate();
		toDay = LocalDate.now();
		mno = ent.getMno();
	}

	public void setWriterId(String id) { writerId = id; }

}
