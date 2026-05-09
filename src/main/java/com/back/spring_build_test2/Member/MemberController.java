package com.back.spring_build_test2.Member;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/signup")
    public String signup(MemberCreateForm memberCreateForm){

        return "signup";
    }

    @PostMapping("/signup")
    public String setSignUp(@Valid MemberCreateForm memberCreateForm, BindingResult bindingResult
    ){
        if(bindingResult.hasErrors())
            return "signup";

        if(!memberCreateForm.getPassword1().equals(memberCreateForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect", "비밀번호가 일치하지 않습니다.");
            return  "signup";
        }

        memberService.create(
                memberCreateForm.getUsername(),
                memberCreateForm.getNickname(),
                memberCreateForm.getPassword1()
        );

        return "redirect:/article/list";
    }

    @GetMapping("/login")
    public String login(MemberLoginForm memberLoginForm){
        return "login";
    }

    @PostMapping("/login")
    public String login(
            @Valid MemberLoginForm memberLoginForm,
            BindingResult bindingResult,
            HttpSession session
    ) {

        if(bindingResult.hasErrors()) {
            return "login";
        }

        Member member = memberService.findByUsername(
                memberLoginForm.getUsername()
        );

        if(member == null) {

            bindingResult.reject(
                    "loginFail",
                    "아이디 또는 비밀번호가 올바르지 않습니다."
            );

            return "login";
        }

        boolean matches = passwordEncoder.matches(
                memberLoginForm.getPassword(),
                member.getPassword()
        );

        if(!matches) {

            bindingResult.reject(
                    "loginFail",
                    "아이디 또는 비밀번호가 올바르지 않습니다."
            );

            return "login";
        }

        session.setAttribute("loginMember",member);

        return "redirect:/article/list";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }


    @Getter
    @Setter
    public static class MemberLoginForm{
        @NotBlank(message = "아이디는 필수입니다.")
        private String username;

        @NotBlank(message = "비밀번호는 필수입니다.")
        private String password;
    }
}
