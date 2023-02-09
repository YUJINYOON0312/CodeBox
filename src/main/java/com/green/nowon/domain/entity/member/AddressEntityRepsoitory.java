package com.green.nowon.domain.entity.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressEntityRepsoitory extends JpaRepository<AddressEntity, Long> {

	Optional<AddressEntity> findByMember_mno(long mno);

	Optional<AddressEntity> findByBaseAndMemberId(boolean b, String id);

}
