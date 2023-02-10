package com.green.nowon.domain.entity.cate;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentEntityRepository extends JpaRepository<DepartmentEntity, Long> {

	Optional<DepartmentEntity> findByParentDnoAndDname(Long parentDno, String dName);

	Optional<DepartmentEntity> findByParentDnoNullAndDname(String dName);

	Optional<DepartmentEntity> findByParentDnoOrderByDnameAsc(Long parentDno);

	List<DepartmentEntity> findAllByParentDno(Long parentDno);

	List<DepartmentEntity> findAllByDepth(int i);

}
