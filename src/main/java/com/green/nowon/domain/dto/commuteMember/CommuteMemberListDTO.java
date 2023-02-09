package com.green.nowon.domain.dto.commuteMember;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.green.nowon.domain.entity.attendance.CommuteEntity;
import com.green.nowon.domain.entity.member.MemberEntity;

import lombok.Data;

@Data
public class CommuteMemberListDTO {

	private String gTime;

	private String oTime;

	private LocalDate today;

	private String cType;

	private MemberEntity member;

	private String name;

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d일 a h시간 mm분");

	public CommuteMemberListDTO(CommuteEntity e) {
		if (e == null) {
			gTime = LocalDateTime.now().format(formatter);
			oTime = LocalDateTime.now().format(formatter);
			today = LocalDate.now();
			cType = "첫출근";
			name = "첫출근";
		} else {
			gTime = e.getGTime().format(formatter);
			oTime = e.getOTime().format(formatter);
			today = e.getToday();
			cType = e.getCType();
			name = e.getMember().getName();
		}
	}

}
