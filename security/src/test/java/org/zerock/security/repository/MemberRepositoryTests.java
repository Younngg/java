package org.zerock.security.repository;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.zerock.security.domain.Member;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class MemberRepositoryTests {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Test
	public void testRead() {

		Optional<Member> result = memberRepository.getWithRoles("member100");

		Member member = result.orElseThrow();

		log.info(member);
		log.info(member.getRoleSet());

		member.getRoleSet().forEach(memberRole -> log.info(memberRole.name()));

	}

	// @Test
	// public void insertMembers() {
	// 	IntStream.rangeClosed(1, 100).forEach(i -> {
	// 		Member member = Member.builder()
	// 			.mid("member" + i)
	// 			.mpw(passwordEncoder.encode("1111"))
	// 			.email("email" + i + "@aaa.bbb")
	// 			.build();
	//
	// 		member.addRole(MemberRole.USER);
	//
	// 		if(i >= 90) {
	// 			member.addRole(MemberRole.ADMIN);
	// 		}
	// 		memberRepository.save(member);
	// 	});
	// }
}
