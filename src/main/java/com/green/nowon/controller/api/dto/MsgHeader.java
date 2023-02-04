package com.green.nowon.controller.api.dto;

import lombok.Data;

@Data
public class MsgHeader {
	private String headerMsg;
	private String headerCd;
	private int itemCount;
}
