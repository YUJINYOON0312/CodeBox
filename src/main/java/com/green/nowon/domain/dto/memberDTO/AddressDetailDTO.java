package com.green.nowon.domain.dto.memberDTO;

import com.green.nowon.domain.entity.member.AddressEntity;

import lombok.Data;

/**
 * @author LeeYongJu
 */
@Data
public class AddressDetailDTO {

	private String postcode;

	private String roadAddress;

	private String jibunAddress;

	private String detailAddress;

	private String extraAddress;

	private boolean base;

	public AddressDetailDTO(AddressEntity e) {
		postcode = e.getPostcode();
		roadAddress = e.getRoadAddress();
		jibunAddress = e.getJibunAddress();
		detailAddress = e.getDetailAddress();
		extraAddress = e.getExtraAddress();
		base = e.isBase();
	}

}
