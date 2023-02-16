package com.green.nowon.controller.member;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.green.nowon.service.employee.EmployeeService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class EmployeeController {

	private final EmployeeService eService;

	@GetMapping("/employee") // 카테고리 리스트롤 사용할 예정
	public String employeeList() {
		return "member/employee-list";
	}

	@GetMapping("/employee/{parentDno}")
	public String employeeListCate(@PathVariable long parentDno, Model model) {
		eService.listView(parentDno, model);
		return "member/tag-list";
	}

}
