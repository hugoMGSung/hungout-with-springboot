package com.hugo83.tinylibrary.repository;

// import java.time.LocalDate;
import java.util.Optional;
// import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.security.crypto.password.PasswordEncoder;

import com.hugo83.tinylibrary.entity.Member;
// import com.hugo83.tinylibrary.entity.MemberRole;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class MemberRepositoryTests {
	@Autowired
	private MemberRepository memberRepository;

	// @Autowired
	// private PasswordEncoder passwordEncoder;

	// @Test
	// public void insertMember() {
	// 	IntStream.rangeClosed(1, 10).forEach(i -> {
	// 		Member member = Member.builder()
	// 					.email("test" + i + "@test.com")
	// 				.password(passwordEncoder.encode("123456"))
	// 					.joinDate(LocalDate.now())
	// 					.mName("홍길길" + i)
	// 				.build();
	// 		log.info(member.getEmail());

	// 		member.addRole(MemberRole.USER);

	// 		if (i > 8) {
	// 			member.addRole(MemberRole.ADMIN);
	// 		}
	// 		memberRepository.save(member);
	// 	});		
	// }

	@Test
	public void testRead() {
		Optional<Member> result = memberRepository.getEmailWithRoles("test10@test.com");

		Member member = result.orElseThrow();

		log.info("MEMBER :::::::  " + member.getRoleSet());
		member.getRoleSet().forEach(memberRole -> log.info(memberRole.name()));
	}

}
