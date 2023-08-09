package com.wanted.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wanted.backend.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String> {
	Optional<Member> findByEmail(String email);
}
