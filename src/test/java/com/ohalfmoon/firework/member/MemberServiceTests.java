package com.ohalfmoon.firework.member;

import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.persistence.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Slf4j
@SpringBootTest
public class MemberServiceTests {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void memberSaveTests() {
        memberRepository.save(MemberEntity.builder()
                        .username("cksdyd")
                        .password("1234")
                        .email("cksdydsla93@gmail.com")
                        .phoneNum("01012341234")
//                        .deptNo(1L)
//                        .positionNo(1L)
                        .name("양찬용")
                        .birthdate(new Date())
                        .startdate(new Date())
                .build());
    }

    // 23.06.13 방한솔
    // 회원 단일조회 테스트
    @Transactional
    @Test
    public void findMemberTest(){
        MemberEntity memberEntity = memberRepository.findByUsername("ycy123");

        log.info("{}", memberEntity);
    }
}
