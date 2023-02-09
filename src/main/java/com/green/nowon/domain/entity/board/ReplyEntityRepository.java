package com.green.nowon.domain.entity.board;

import org.springframework.data.jpa.repository.JpaRepository;

import com.green.nowon.domain.dto.board.reply.ReplySaveDTO;

public interface ReplyEntityRepository extends JpaRepository<ReplyEntity, Long> {

	void save(ReplySaveDTO dto);

}
