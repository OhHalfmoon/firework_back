package com.ohalfmoon.firework.config.auth;

import com.ohalfmoon.firework.dto.member.MemberDTO;
import com.ohalfmoon.firework.dto.member.MemberUpdateDTO;
import com.ohalfmoon.firework.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

/**
 * packageName :  com.ohalfmoon.firework.config.auth
 * fileName : CheckUsernameValidator
 * author :  ycy
 * date : 2023-06-20
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-06-20                ycy             최초 생성
 */

@Component
@RequiredArgsConstructor
public class CheckUsernameValidator extends AbstractValidator<MemberDTO> {

    private final MemberRepository memberRepository;

    @Override
    protected void doValidate(MemberDTO dto, Errors errors) {
        if(memberRepository.existsByUsername(dto.getUsername())) {
            errors.rejectValue("username", "아이디 중복 오류", "이미 사용중인 아이디입니다.");
        }
    }
}
