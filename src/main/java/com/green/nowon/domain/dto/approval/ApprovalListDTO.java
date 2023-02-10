package com.green.nowon.domain.dto.approval;

import java.sql.Date;

import com.green.nowon.domain.entity.approval.ApprovalEntity;
import com.green.nowon.domain.entity.member.MemberEntity;

import lombok.Data;

@Data
public class ApprovalListDTO {

	private long ano;

	private String title;

	private String content;

	private Date date;

	private String status;

	private MemberEntity mno;

	private long mnoo;

	private String mName;

	public ApprovalListDTO(ApprovalEntity e) {
		date = e.getDate();
		ano = e.getAno();
		title = e.getTitle();
		content = e.getContent();
		status = e.getStatus();

		if (e.getMno() != null) {
			mno = e.getMno();
			mnoo = mno.getMno();
			mName = mno.getName();
		}

	}

}
