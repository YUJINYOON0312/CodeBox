package com.green.nowon.domain.entity.cate;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentMemberEntityRepository extends JpaRepository<DepartmentMemberEntity, Long> {

	List<DepartmentMemberEntity> findAllByDepartment_dno(Long dno);

	List<DepartmentMemberEntity> findByMemberMno(long arr);

	Optional<DepartmentMemberEntity> findAllByMemberMno(long arr);

	List<DepartmentMemberEntity> findAllByMember_mno(long mno);

	Optional<DepartmentMemberEntity> findByMember_mnoAndDepartment_depth(long mno, int cateNo);

	@Transactional
	List<DepartmentMemberEntity> deleteByMember_mno(long mno);

}