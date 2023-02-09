package com.green.nowon.domain.dto.memberDTO;

import com.green.nowon.domain.entity.member.MemberEntity;

import lombok.Data;

@Data
public class MemberDetailDTO {

	private long mno;

	private String id;

	private String name;

	private String pass;

	private String phone;

	public MemberDetailDTO(MemberEntity e) {
		mno = e.getMno();
		id = e.getId();
		name = e.getName();
		pass = e.getPass();
		phone = e.getPhone();

	}

}
