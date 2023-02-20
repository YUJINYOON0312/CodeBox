package com.green.nowon.domain.entity.board;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardImgEntityRepository extends JpaRepository<BoardImgEntity, Long> {

	Optional<BoardImgEntity> findByUrlAndOrgName(String pATH, String fileName);

	void deleteByUrlAndNewName(String pATH, String fileName);

	void deleteByBoard_bno(long bno);

	Optional<BoardImgEntity> findByBoard_bno(long bno);

}