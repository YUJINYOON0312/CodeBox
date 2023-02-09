package com.green.nowon.domain.dto.board;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.green.nowon.domain.entity.board.GeneralBoardEntity;

import lombok.Getter;

@Getter
public class GenBoardListDTO {

	private long bno;

	private String title;

	private int readCount;

	// private String writer;
	private LocalDateTime updatedDate;

	private LocalDate toDay;

	private String writerId;

	// Entity를 -> BoardListDTO(BoardEntity ent)
	public GenBoardListDTO(GeneralBoardEntity ent) {

		bno = ent.getBno();
		title = ent.getTitle();
		readCount = ent.getReadCount();
		// this.writer = ent.getMember().getName(); //member의 name이 작성자임
		updatedDate = ent.getUpdatedDate();
		toDay = LocalDate.now();
		writerId = String.valueOf(ent.getMno());// .getId();
	}

}