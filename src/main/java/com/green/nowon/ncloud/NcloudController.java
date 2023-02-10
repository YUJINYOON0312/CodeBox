package com.green.nowon.ncloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NcloudController {

	// propertis
	@Value("${navar.ncloud.client-id}")
	private String clientId;

	@Value("${naver.ncloud.redirect-uri}")
	private String redirectUri;

	@Value("${naver.ncloud.scope}")
	private String scope;

	@GetMapping("/naver/dept")
	public String dept(Model model) {

		model.addAttribute("clientId", clientId);
		model.addAttribute("redirectUri", redirectUri);
		model.addAttribute("scope", scope);

		return "ncloud/dept";
	}

	@GetMapping("/naver/oauth2")
	public String naverOauth2(String code, String state) {
		return "ncloud/dept";
	}

}
