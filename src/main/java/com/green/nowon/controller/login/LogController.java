package com.green.nowon.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.green.nowon.domain.dto.memberDTO.AddressInsertDTO;
import com.green.nowon.domain.dto.memberDTO.MemberInsertDTO;
import com.green.nowon.service.MemberService;
import com.green.nowon.service.mypage.MyPageService;

import lombok.RequiredArgsConstructor;

/**
 * @author LeeYongJu Log관련 컨트롤러 기능 : 로그인 , 회원가입
 */
@RequiredArgsConstructor
@Controller
public class LogController {

	private final MyPageService service;

	private final MemberService mService;

	/**
	 * 로그인창 이동
	 *
	 * @return 로그인창
	 */
	@GetMapping("/login")
	public String login() {
		return "/log/login";
	}

	/**
	 * 회원가입창이동
	 *
	 * @return 회원가입창
	 */
	@GetMapping("/signup")
	public String signup() {
		return "/log/signup";
	}

	/**
	 * 회원가입 버튼시 회원등록
	 *
	 * @return 로그인창
	 */
	@PostMapping("/signup")
	public String registMember(MemberInsertDTO mdto, AddressInsertDTO adto) {
		// 회원가입 만들기! 프로필 이미지 삽입X
		mService.save(mdto, adto);
		return "redirect:/login";
	}

}