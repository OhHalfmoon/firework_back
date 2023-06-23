package com.ohalfmoon.firework.repository;

import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.model.State;
import com.ohalfmoon.firework.persistence.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Slf4j
@SpringBootTest
public class MemberRepositoryTests {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder encoder;

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
                        .birthdate(LocalDate.now())
                        .startdate(LocalDate.now())
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

    @Test
    @DisplayName("state가 0인 회원 조회")
    public void zeroStateMemberTest() {
        List<MemberEntity> entity = memberRepository.findAllByState(State.WATING);
        log.info("state = 0 : {}", entity);
    }

    @Test
    @DisplayName("자신 빼고 다른 사람들 조회")
    public void findAllTest() {
        Pageable pageable = PageRequest.of(1, 5);
        Page<MemberEntity> memberEntities = memberRepository.findAllByUserNoNotLike(1L, pageable);
        log.info("{}", memberEntities.getContent());
    }
}
