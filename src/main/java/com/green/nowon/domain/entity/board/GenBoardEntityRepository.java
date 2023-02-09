package com.green.nowon.domain.entity.board;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenBoardEntityRepository extends JpaRepository<GeneralBoardEntity, Long> {

	List<GeneralBoardEntity> findAllByOrderByBnoDesc();

	List<GeneralBoardEntity> findByMnoOrderByBnoDesc(long mno);

	Page<GeneralBoardEntity> findAllByOrderByBnoDesc(Pageable pageable);

	List<GeneralBoardEntity> findByTitleContaining(String keyword); // 자유게시물 검색

	Page<GeneralBoardEntity> findByTitleContaining(String keyword, Pageable pageable);// 게시물 검색, 페이징

	// 조회수 쿼리
	// @Modifying
	// @Query("update GeneralBoardEntity b set b.readCount = b.readCount +1 where
	// b.bno = :bno")
	// int genUpdateReadCount(@Param("bno") Long bno);

}
