package com.green.nowon.domain.dto.memberDTO;

import com.green.nowon.domain.entity.cate.PositionEntity;
import com.green.nowon.domain.entity.member.MemberEntity;

import lombok.Data;

@Data
public class SalaryListDTO {

	private long mno;

	private String id;

	private String email;

	private String name;

	private String pass;

	private String phone;

	private String profileUrl;

	private String newName;

	private String orgName;

	private PositionEntity pno;

	private String pname;

	private int normalSalary;

	// 감소되는금액
	private double minSal;

	// 증가하는금액
	private int none;

	private double sal1y;

	private double sal2y;

	private double sal3y;

	private double sal4y;

	private double sal5y;

	// nomalSal에서 감소되는금액을 뺀 금액
	private int minSalTot;

	// 사원관리페이지에서 급여등록 후 멤버엔티티에 저장되는 컬럼
	private double bonus;// 증가되는 금액

	private int totSal;// 모든걸 계산한 후 최종급여

	String role;

	public SalaryListDTO(MemberEntity e) {
		mno = e.getMno();
		id = e.getId();
		name = e.getName();
		pass = e.getPass();
		phone = e.getPhone();
		email = e.getEmail();
		role = e.getRoles().toString();

		if (e.getPno() != null) { // 직책,기본금,마이너스,플러스금액 계산

			pname = e.getPno().getPName();
			normalSalary = e.getPno().getNormalSalary();

			minSal = e.getPno().getNormalSalary() * 0.09;// -금액 고정

			none = 0;// 신입
			sal1y = e.getPno().getNormalSalary() * 0.045;// 기본급의 3% 1년차
			sal2y = e.getPno().getNormalSalary() * 0.09;// 기본급의 6% 2년차
			sal3y = e.getPno().getNormalSalary() * 0.135;// 기본급의 9% 3년차
			sal4y = e.getPno().getNormalSalary() * 0.18;// 기본급의 12% 4년차
			sal5y = e.getPno().getNormalSalary() * 0.225;// 기본급의 15% 5년차

			minSalTot = (int) (e.getPno().getNormalSalary() - minSal);// 세금이 포함된 월급

			bonus = e.getBoList();
			totSal = e.getTotSalary();

		} else {
			pname = "없음";
			normalSalary = 0;

			minSal = 0;// 세금

			sal1y = 0;
			sal2y = 0;
			sal3y = 0;
			sal4y = 0;
			sal5y = 0;

			minSalTot = 0;

			bonus = 0;
			totSal = 0;

		}
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 스타트겟프로파일");

		if (e.getProfile() != null) {
			profileUrl = e.getProfile().getUrl() + e.getProfile().getNewName();
			orgName = e.getProfile().getOrgName();
			newName = e.getProfile().getNewName();

		}

	}

}