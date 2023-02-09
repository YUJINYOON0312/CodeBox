package com.green.nowon.domain.dto.memberDTO;

import com.green.nowon.domain.entity.cate.DepartmentMemberEntity;
import com.green.nowon.domain.entity.member.MemberEntity;

import lombok.Data;

@Data
public class DepartmentMemberListDTO {

	private long mno;

	private String id;

	private String name;

	private String pass;

	private String phone;

	private String email;

	private String profileUrl;

	// private String pName;
	private String dName;

	public DepartmentMemberListDTO(MemberEntity e) {
		mno = e.getMno();
		id = e.getId();
		name = e.getName();
		pass = e.getPass();
		phone = e.getPhone();
		email = e.getEmail();
		// this.profileUrl=e.getProfile().getUrl()+e.getProfile().getNewName();
		if (e.getProfile() != null) profileUrl = e.getProfile().getUrl() + e.getProfile().getNewName();

	}

	public DepartmentMemberListDTO(DepartmentMemberEntity e) {
		this(e.getMember());
		dName = e.getDepartment().getDname();
	}

}
