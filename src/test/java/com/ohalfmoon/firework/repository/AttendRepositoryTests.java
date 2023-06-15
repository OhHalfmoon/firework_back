package com.ohalfmoon.firework.repository;

import com.ohalfmoon.firework.model.AttendEntity;
import com.ohalfmoon.firework.persistence.AttendRepository;
import com.ohalfmoon.firework.persistence.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
/**
 * packageName    : com.ohalfmoon.firework.repository
 * fileName       : AttendRepositoryTests
 * author         : 이지윤
 * date           : 2023/06/15
 * description    : Attend 레파지토리 테스트
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/15        이지윤           최초 생성
 */
@SpringBootTest
@Slf4j
public class AttendRepositoryTests {
    @Autowired
    private AttendRepository attendRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Transactional
    public void testSave() {
        AttendEntity attendEntity = AttendEntity.builder()
                .memberEntity(memberRepository.findById(1L).orElseThrow(()-> new IllegalArgumentException("실패")))
                .godate(new Date())
                .build();
        attendRepository.save(attendEntity);
    }

    @Test
    public void testUpdate() {
        AttendEntity attendEntity = attendRepository.findById(3L).orElseThrow(()-> new IllegalArgumentException("실패"));
        attendEntity.update(new Date());
        attendRepository.save(attendEntity);
    }

}
