package com.green.nowon.security;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.green.nowon.domain.entity.cate.DepartmentMemberEntity;
import com.green.nowon.domain.entity.cate.DepartmentMemberEntityRepository;
import com.green.nowon.domain.entity.member.MemberEntityRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {

	private final MemberEntityRepository repo;

	private final DepartmentMemberEntityRepository drepo;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		List<DepartmentMemberEntity> aaa = drepo.findByMemberMno(repo.findById(username).get().getMno());

		DepartmentMemberEntity aa = aaa.get(aaa.size() - 1);

		return new MyUserDetails(repo.findById(username).orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 이메일")),
				aa);
	}

}
