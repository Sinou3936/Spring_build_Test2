package com.back.spring_build_test2.Member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRespoitory extends JpaRepository<Member, Integer> {

    Member findByUsername(String username);
}
