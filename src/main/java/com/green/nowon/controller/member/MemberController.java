package com.green.nowon.controller.member;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.green.nowon.domain.dto.memberDTO.MemberUpdateDTO;
import com.green.nowon.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MemberController {

	private final MemberService memberService;

	@ResponseBody
	@PostMapping("/mypage/temp-upload")
	public Map<String, String> memberDetailImg(MultipartFile img) {
		return memberService.fileTempUpload(img);
	}

	@GetMapping("/mypage/{mno}")
	public String detail(@PathVariable long mno, Model model) {
		memberService.detail(mno, model);
		return "mypage/employee-detail";
	}

	@PostMapping("/mypage/{mno}/update")
	public String update(@PathVariable long mno, MemberUpdateDTO dto) {
		memberService.update(mno, dto);
		return "redirect:/mypage/info/{mno}";
	}

	// 오시는길
	@GetMapping("/api/map")
	public String map() {
		return "api/map/map";
	}

}
