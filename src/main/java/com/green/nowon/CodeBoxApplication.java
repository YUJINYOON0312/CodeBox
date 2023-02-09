package com.green.nowon;

import java.time.LocalDate;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.green.nowon.domain.entity.cate.DepartmentEntity;
import com.green.nowon.domain.entity.cate.DepartmentEntityRepository;
import com.green.nowon.domain.entity.cate.DepartmentMemberEntity;
import com.green.nowon.domain.entity.cate.DepartmentMemberEntityRepository;
import com.green.nowon.domain.entity.member.AddressEntity;
import com.green.nowon.domain.entity.member.AddressEntityRepository;
import com.green.nowon.domain.entity.member.MemberEntity;
import com.green.nowon.domain.entity.member.MemberEntityRepository;
import com.green.nowon.security.MyRole;

@SpringBootApplication
public class CodeBoxApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeBoxApplication.class, args);
	}

	@Bean
	ApplicationRunner applicationRunner(MemberEntityRepository repo, PasswordEncoder pe,
			AddressEntityRepository addressrepo, DepartmentMemberEntityRepository dmRepo,
			DepartmentEntityRepository departmentRepo) {
		return args -> {
			if (repo.findById("admin2").isPresent())
				return;
			else {
				var a = repo.save(MemberEntity.builder()
						.name("관리자")
						.id("admin2")
						.email("admin2@greengames.shop")
						.pass(pe.encode("1234"))
						.phone("010-1234-5608")
						.hireDate(LocalDate.now())
						.build()
						.addRole(MyRole.ADMIN));
				addressrepo.save(AddressEntity.builder()
						.postcode("01849")
						.roadAddress("서울 노원구 공릉로 95")
						.jibunAddress("서울 노원구 공릉동 661-11")
						.detailAddress("노원그린아카데미")
						.extraAddress("(공릉동)")
						.member(a)
						.build());
				var d = departmentRepo.save(DepartmentEntity.builder().dname("코드박스").depth(1).parent(null).build());
				dmRepo.save(DepartmentMemberEntity.builder().department(d).member(a).build());
			}
			;
		};
	}

}
