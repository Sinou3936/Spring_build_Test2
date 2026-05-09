package com.back.spring_build_test2.Member;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRespoitory memberRespoitory;

    public Member create(String username, String nickname, String password)
    {
        Member member = new Member();
        member.setUsername(username);
        member.setNickname(nickname);
        member.setPassword(password);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        member.setPassword(passwordEncoder.encode(password));

        memberRespoitory.save(member);

        return member;
    }


    public Member findByUsername(@NotBlank(message = "아이디는 필수입니다.") String username) {
        return memberRespoitory.findByUsername(username);
    }
}
