package org.zerock.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.security.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

	@EntityGraph(attributePaths = "roleSet")
	@Query("select m from Member m where m.mid = :mid and m.social = false")
	Optional<Member> getWithRoles(String mid);

	@EntityGraph(attributePaths = "roleSet")
	Optional<Member> findByEmail(String email);
}
