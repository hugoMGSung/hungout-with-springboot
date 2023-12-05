package com.hugo83.tinylibrary.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
// import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Modifying;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
// import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Query;

import com.hugo83.tinylibrary.entity.Member;

// import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {

	@EntityGraph(attributePaths = "roleSet")
	@Query("SELECT m FROM Member m WHERE m.email = :email")
	Optional<Member> getEmailWithRoles(String email);
}
