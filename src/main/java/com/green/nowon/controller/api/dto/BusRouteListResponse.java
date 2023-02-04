package com.green.nowon.controller.api.dto;

import lombok.Data;

@Data
public class BusRouteListResponse {
	private ComMsgHeader comMsgHeader;
	private MsgHeader msgHeader;
	private MsgBody msgBody;
}
