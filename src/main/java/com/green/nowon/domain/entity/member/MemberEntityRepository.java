package com.green.nowon.domain.entity.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberEntityRepository extends JpaRepository<MemberEntity, Long> {

	Optional<MemberEntity> findById(String id);

	Optional<MemberEntity> findAllById(String email);

	Optional<MemberEntity> findByName(String name);

}
