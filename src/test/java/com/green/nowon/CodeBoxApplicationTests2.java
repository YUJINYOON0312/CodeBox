package com.green.nowon;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.green.nowon.domain.entity.approval.ApprovalEntityRepository;
import com.green.nowon.domain.entity.attendance.AttendanceEntityRepository;
import com.green.nowon.domain.entity.attendance.CommuteEntityRepository;
import com.green.nowon.domain.entity.board.BoardEntity;
import com.green.nowon.domain.entity.board.BoardEntityRepository;
import com.green.nowon.domain.entity.board.BoardImgEntityRepository;
import com.green.nowon.domain.entity.board.GenBoardEntityRepository;
import com.green.nowon.domain.entity.board.ReplyEntityRepository;
import com.green.nowon.domain.entity.cate.DepartmentEntity;
import com.green.nowon.domain.entity.cate.DepartmentEntityRepository;
import com.green.nowon.domain.entity.cate.DepartmentMemberEntity;
import com.green.nowon.domain.entity.cate.DepartmentMemberEntityRepository;
import com.green.nowon.domain.entity.cate.PositionRepository;
import com.green.nowon.domain.entity.chatbot.AnswerRepository;
import com.green.nowon.domain.entity.chatbot.ChatBotIntentionRepository;
import com.green.nowon.domain.entity.member.AddressEntity;
import com.green.nowon.domain.entity.member.AddressEntityRepository;
import com.green.nowon.domain.entity.member.MemberEntity;
import com.green.nowon.domain.entity.member.MemberEntityRepository;
import com.green.nowon.domain.entity.member.ProfileEntityRepository;
import com.green.nowon.domain.entity.schedule.CalendarEntity2Repository;
import com.green.nowon.domain.entity.schedule.CalendarEntityRepository;
import com.green.nowon.security.MyRole;

// @formatter:off
@SpringBootTest@SuppressWarnings("unused")@Rollback(false)@Transactional@Commit
class CodeBoxApplicationTests2 {
	//=================================================================
	/**
	 * @aper 승인
	 */
	@Autowired private ApprovalEntityRepository aper;
	/**
	 * @ar 답변
	 */
	@Autowired private AnswerRepository ar;
	/**
	 * @ater 출근
	 */
	@Autowired private AttendanceEntityRepository ater;
	/**
	 * @ader 주소
	 */
	@Autowired private AddressEntityRepository	ader;
	/**
	 * @ber 게시판
	 */
	@Autowired private BoardEntityRepository ber;
	/**
	 * @bier 게시판이미지
	 */
	@Autowired private BoardImgEntityRepository bier;
	/**
	 * @cmer 근태
	 */
	@Autowired private CommuteEntityRepository cmer;
	/**
	 * @cer 달력
	 */
	@Autowired private CalendarEntityRepository cer;
	/**
	 * @ce2r 달력2
	 */
	@Autowired private CalendarEntity2Repository ce2r;
	/**
	 * @cir 의도
	 */
	@Autowired private ChatBotIntentionRepository cir;
	/**
	 * @der 부서
	 */
	@Autowired private DepartmentEntityRepository der;
	/**
	 * @dmer 부서멤버
	 */
	@Autowired private DepartmentMemberEntityRepository dmer;
	/**
	 * @gber 전체게시판
	 */
	@Autowired private GenBoardEntityRepository gber;
	/**
	 * @rer 댓글
	 */
	@Autowired private ReplyEntityRepository rer;
	/**
	 * @mer 멤버
	 */
	@Autowired private MemberEntityRepository mer;
	/**
	 * @pe 패스워드인코더
	 */
	@Autowired private PasswordEncoder pe;
	/**
	 * @pr 직급
	 */
	@Autowired private PositionRepository pr;
	/**
	 * @per 프로필
	 */
	@Autowired private ProfileEntityRepository per;
	// @Test void 임시() {aper.flush();ater.flush();ader.flush();ber.flush();bier.flush();cmer.flush();cer.flush();ce2r.flush();cir.flush();der.flush();dmer.flush();gber.flush();rer.flush();mer.flush();pe.encode("a");pr.flush();per.flush();}
	// =====================================================================================================================
	private String krName() {final List<String> lastName = Arrays.asList("김", "이", "박", "최", "정", "강", "조", "윤", "장", "임", "한", "오", "서", "신","권", "황", "안", "송", "류", "전", "홍", "고", "문", "양", "손", "배", "조", "백", "허", "유", "남", "심", "노", "정", "하", "곽","성", "차", "주", "우", "구", "신", "임", "나", "전", "민", "유", "진", "지", "엄", "채", "원", "천", "방", "공", "강", "현", "함","변", "염", "양", "변", "여", "추", "노", "도", "소", "신", "석", "선", "설", "마", "길", "주", "연", "방", "위", "표", "명", "기","반", "왕", "금", "옥", "육", "인", "맹", "제", "모", "장", "남", "탁", "국", "여", "진", "어", "은", "편", "구", "용");final List<String> name = Arrays.asList("가", "강", "건", "경", "고", "관", "광", "구", "규", "근", "기", "길", "나", "남", "노","누", "다", "단", "달", "담", "대", "덕", "도", "동", "두", "라", "래", "로", "루", "리", "마", "만", "명", "무", "문", "미", "민","바", "박", "백", "범", "별", "병", "보", "빛", "사", "산", "상", "새", "서", "석", "선", "설", "섭", "성", "세", "소", "솔", "수","숙", "순", "숭", "슬", "승", "시", "신", "아", "안", "애", "엄", "여", "연", "영", "예", "오", "옥", "완", "요", "용", "우", "원","월", "위", "유", "윤", "율", "으", "은", "의", "이", "익", "인", "일", "잎", "자", "잔", "장", "재", "전", "정", "제", "조", "종","주", "준", "중", "지", "진", "찬", "창", "채", "천", "철", "초", "춘", "충", "치", "탐", "태", "택", "판", "하", "한", "해", "혁","현", "형", "혜", "호", "홍", "화", "환", "회", "효", "훈", "휘", "희", "운", "모", "배", "부", "림", "봉", "혼", "황", "량", "린","을", "비", "솜", "공", "면", "탁", "온", "디", "항", "후", "려", "균", "묵", "송", "욱", "휴", "언", "령", "섬", "들", "견", "추","걸", "삼", "열", "웅", "분", "변", "양", "출", "타", "흥", "겸", "곤", "번", "식", "란", "더", "손", "술", "훔", "반", "빈", "실","직", "흠", "흔", "악", "람", "뜸", "권", "복", "심", "헌", "엽", "학", "개", "롱", "평", "늘", "늬", "랑", "얀", "향", "울", "련");Collections.shuffle(lastName);Collections.shuffle(name);return lastName.get(0) + name.get(0) + name.get(1);}
	private String krPn() {final List<String> pNum = Arrays.asList("010", "011", "012", "013");Collections.shuffle(pNum);return pNum.get(0);}private String randomFourDigitNum() {return (int) (Math.random() * 8999) + 1000 + "";}
	private String krPhoneNum(){return krPn()+randomFourDigitNum()+randomFourDigitNum();}
	private final String random = UUID.randomUUID().toString().substring(0, 5);
	private final String postalCode=Integer.toString((int) (Math.random() * 89999) + 10000);
	private final LocalDate birth=LocalDate.of((int) (Math.random() * 50) + 1950, (int) (Math.random() * 11) + 1,(int) (Math.random() * 27) + 1);
	// =================================================================
	// @Transactional @Commit @Rollback(false)
	// @formatter:on
	// =================================================================

	@Test
	void 유저_어드민_등록() {
		IntStream.range(0, 10).forEach(t -> {
			final var r = random;
			final var deptName = "코드박스";
			if (!mer.findById(r).isPresent()) {
				var member = mer.save(MemberEntity.builder()
						.id(r)
						.email(r + "@codebox.codebox")
						.name(krName())
						.phone(krPhoneNum())
						.hireDate(
								LocalDate.of((int) (Math.random() * 15) + 2007, (int) (Math.random() * 11) + 1, (int) (Math.random() * 27) + 1))
						.pass(pe.encode("1234"))
						.build()
						.addRole(t % 2 == 0 ? MyRole.ADMIN : MyRole.USER));
				ader.save(AddressEntity.builder()
						.postcode(postalCode)
						.roadAddress("서울 노원구 공릉로" + random)
						.jibunAddress("서울 노원구 공릉동" + random)
						.detailAddress("노원그린아카데미")
						.extraAddress("(공릉동)" + birth)
						.member(member)
						.build());
				final var dept = der.findByParentDnoNullAndDname(deptName).isPresent() ? der.findById(1L).orElseThrow()
						: der.save(DepartmentEntity.builder().dname(deptName).depth(1).parent(null).build());
				dmer.save(DepartmentMemberEntity.builder().department(dept).member(member).build());
				ber.save(BoardEntity.builder().title(random).content(random).member(member).build());
			}
			System.out.println("중복아이디");
		});
	}

}
