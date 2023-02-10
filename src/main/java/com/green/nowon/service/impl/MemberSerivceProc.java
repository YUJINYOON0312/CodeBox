package com.green.nowon.service.impl;

import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.green.nowon.domain.dto.memberDTO.AddressDTO;
import com.green.nowon.domain.dto.memberDTO.AddressInsertDTO;
import com.green.nowon.domain.dto.memberDTO.MemberInsertDTO;
import com.green.nowon.domain.dto.memberDTO.MemberUpdateDTO;
import com.green.nowon.domain.dto.memberDTO.SalaryListDTO;
import com.green.nowon.domain.entity.cate.DepartmentEntity;
import com.green.nowon.domain.entity.cate.DepartmentMemberEntity;
import com.green.nowon.domain.entity.cate.DepartmentMemberEntityRepository;
import com.green.nowon.domain.entity.member.AddressEntityRepository;
import com.green.nowon.domain.entity.member.MemberEntity;
import com.green.nowon.domain.entity.member.MemberEntityRepository;
import com.green.nowon.domain.entity.member.ProfileEntityRepository;
import com.green.nowon.security.MyRole;
import com.green.nowon.service.MemberService;
import com.green.nowon.util.MyFileUtils;

@Service
public class MemberSerivceProc implements MemberService {

	@Value("${file.location.temp}")
	private String locationTemp;

	@Value("${file.location.upload}")
	private String locationUpload;

	@Autowired
	private MemberEntityRepository memberRepo;

	@Autowired
	private AddressEntityRepository addressRepo;

	@Autowired
	private ProfileEntityRepository ProfileRepo;

	@Autowired
	private DepartmentMemberEntityRepository dmRepo;

	@Autowired
	private PasswordEncoder pe;

	@Override
	public void save(final MemberInsertDTO mdto, final AddressInsertDTO adto) {
		memberRepo.save(mdto.signin(pe).addRole(MyRole.USER)// .addRole(MyRole.ADMIN)
		);// 멤버 저장
		final String id = mdto.getId();
		addressRepo.save(adto.signin().member(memberRepo.findById(id).orElseThrow()));// 주소저장
		final long mno = memberRepo.findById(id).orElseThrow().getMno();// 사번
		dmRepo.save(DepartmentMemberEntity.builder() // 기본 부서등록
				.department(DepartmentEntity.builder().dno(1).build())
				.member(MemberEntity.builder().mno(mno).build())
				.build());

	}

	// 통합DTO사용
	@Transactional
	@Override
	public void detail(final long mno, final Model model) {
		model.addAttribute("detail", memberRepo.findById(mno).map(SalaryListDTO::new).orElseThrow());
		model.addAttribute("aDetail", addressRepo.findByMemberMno(mno).map(AddressDTO::new).orElseThrow());
	}

	@Override
	public Map<String, String> fileTempUpload(final MultipartFile img) {
		return MyFileUtils.fileUpload(img, locationTemp);
	}

	@Transactional
	@Override
	public void update(final long mno, final MemberUpdateDTO dto) {
		MemberEntity entityImg = null;
		final Optional<MemberEntity> result = memberRepo.findById(mno);
		if (result.isPresent()) {
			final MemberEntity entity = result.get();
			entity.update(dto).setPass(pe.encode(dto.getPass()));
			entityImg = memberRepo.save(entity);
			ProfileRepo.deleteByMember_mno(mno);
			dto.toItemListImgs(entityImg, locationUpload).forEach(ProfileRepo::save);
		}
	}

}
