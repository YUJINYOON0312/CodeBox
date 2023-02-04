package com.green.nowon.controller.api.dto;

import lombok.Data;

@Data
public class ComMsgHeader {
	
	private String responseMsgID;
	private String responseTime;
	private String requestMsgID;
	private String returnCode;
	private String successYN;
	private String errMsg;

}
