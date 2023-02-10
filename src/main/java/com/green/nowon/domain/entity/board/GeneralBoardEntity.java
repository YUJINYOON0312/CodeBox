package com.green.nowon.domain.entity.board;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.green.nowon.domain.dto.board.GenBoardUpdateDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "gg_gen_general_board_seq", sequenceName = "gg_general_board_seq", initialValue = 1, allocationSize = 1)
@Table(name = "gg_general_board")
@Entity
public class GeneralBoardEntity extends BaseDateTimeColumns {

	@Id
	@GeneratedValue(generator = "gg_gen_general_board_seq", strategy = GenerationType.SEQUENCE)
	private long bno;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String content;

	private int readCount;
	// 작성자 - MemeberEntity

	// @JoinColumn(name = "mno")
	// @ManyToOne(cascade = CascadeType.DETACH)
	// private MemberEntity member;//작성자

	private long mno;

	// 편의메서드
	public GeneralBoardEntity update(GenBoardUpdateDTO dto) {
		title = dto.getTitle();
		content = dto.getContent();
		return this;
	}

	// 조회수
	public void updateReadCount() {
		readCount++;
	}

}
