package com.green.nowon.domain.entity.cate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentMemberRepository extends JpaRepository<DepartmentMemberEntity, Long> {

	List<DepartmentMemberEntity> findAllByDepartment_dno(Long dno);

}
