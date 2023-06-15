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
                .leavedate(null)
                .build();
        attendRepository.save(attendEntity);
    }


}
