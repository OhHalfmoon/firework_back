package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.member.MemberUpdateDTO;
import com.ohalfmoon.firework.dto.member.MemberUpdatePwDTO;
import com.ohalfmoon.firework.dto.member.MemberUpdateStateDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Date;

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
        memberService.updatePw(1L, dto);
//        }
    }
    @Test
    @DisplayName("회원 상태값 변경 테스트")
    public void updateState() {
        MemberUpdateStateDTO dto = new MemberUpdateStateDTO();
        dto.setState(1);
        dto.toEntity();
        memberService.recognize(2L, dto);
    }

    @Test
    @DisplayName("회원 정보 수정")
    public void updateTest() {
        MemberUpdateDTO dto = new MemberUpdateDTO();
        dto.setName("이지윤2");
        dto.setDeptNo(1L);
        dto.setPositionNo(100L);
        dto.setPhoneNum("01022223333");
        dto.setEmail("bnoc@naver.com");
        dto.toEntity();
        memberService.update(2L, dto);
    }
}
