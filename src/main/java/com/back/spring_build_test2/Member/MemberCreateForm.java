package com.back.spring_build_test2.Member;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberCreateForm {

    @NotBlank(message = "아이디가 필수입니다.")
    @Size(min = 3, max = 20)
    private String username;
    @NotBlank(message = "닉네임은 필수입니다.")
    @Size(min = 2, max = 20)
    private String nickname;
    @NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min = 4, max = 100)
    private String password1;
    @NotBlank(message = "비밀번호 확인은 필수입니다.")
    private String password2;
}
