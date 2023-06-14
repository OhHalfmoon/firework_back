package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.member.MemberUpdatePwDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * packageName :  com.ohalfmoon.firework.service
 * fileName : MemberServiceTest
 * author :  ycy
 * date : 2023-06-14
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-06-14                ycy             최초 생성
 */
@SpringBootTest
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    PasswordEncoder encoder;

    @Test
    @DisplayName("비밀번호 변경 테스트 및 기존 암호화되지 않은 비밀번호 암호화")
    public void updatePwTests() {
        MemberUpdatePwDTO dto = new MemberUpdatePwDTO();
//        for(long i = 1L; i<=14L; i++) {
        dto.setPassword(encoder.encode("1234"));
        dto.toEntity();
        memberService.updatePw(16L, dto);
//        }

    }
}
