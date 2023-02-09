package com.green.nowon.domain.entity.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberEntityRepository2 extends JpaRepository<MemberEntity, Long> {

	Optional<MemberEntity> findById(String id);

	MemberEntity findByMno(long mno);

}
