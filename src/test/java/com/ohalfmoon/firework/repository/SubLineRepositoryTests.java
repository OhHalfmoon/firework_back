package com.ohalfmoon.firework.repository;

import com.ohalfmoon.firework.model.SubLineEntity;
import com.ohalfmoon.firework.persistence.MasterLineRepository;
import com.ohalfmoon.firework.persistence.MemberRepository;
import com.ohalfmoon.firework.persistence.SubLineRepository;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * packageName    : com.ohalfmoon.firework.repository
 * fileName       : SubLineRepositoryTests
 * author         : 이지윤
 * date           : 2023/06/09
 * description    : 서브 라인 레파지토리 테스트
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/09        이지윤           최초 생성
 */

@Slf4j
@SpringBootTest
public class SubLineRepositoryTests {
    @Autowired
    private SubLineRepository subLineRepository;

    @Autowired
    private MasterLineRepository masterLineRepository;

    @Autowired
    private MemberRepository memberRepository;

    @After("")
    public void cleanup() {
        subLineRepository.deleteAll();
    }

    // 서브 라인 추가 - 성공
    @Test
    public void testSave() {
        Integer orderLevel = 1;
        Long lineNo = 1L;
        Long userNo = 1L;

        subLineRepository.save(SubLineEntity.builder()
                .orderLevel(orderLevel)
                .masterLineEntity(masterLineRepository.findById(lineNo).orElse(null))
                .memberEntity(memberRepository.findById(userNo).orElse(null))
                .build());

    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void testUpdate() {
//        Long subLineNo = 2L;
//        SubLineEntity.builder().subLineNo(2L).build().update(3, 1L); //영속성이 안된 객체

        SubLineEntity se = subLineRepository.findById(2L).orElseThrow(()->new RuntimeException("없음"));
        log.info("{}", se);
        se.update(1, 2L);
        log.info("{}", se);
//        subLineRepository.save(update(3, 1L));
    }

    // 서브 라인 subLineNo를 통한 단일 조회 - 성공
    @Test
    public void testFindBySubLineNo(){
        Long subLineNo = 2L;
        subLineRepository.findById(subLineNo);
        log.info("{}", subLineRepository.findById(subLineNo));
    }

    @Test
    public void testGetList() {
        subLineRepository.findAll();
        log.info("{}", subLineRepository.findAll());
    }

    @Test
    public void testGetListByLineNo() {
        Long lineNo = 1L;
        subLineRepository.findAllByMasterLineEntity_LineNo(lineNo);
        log.info("{}", subLineRepository.findAllByMasterLineEntity_LineNo(lineNo));
    }

    // 서브 라인 삭제 - 성공
    @Test
    public void testDelete() {
        Long subLineNo = 1L;
        subLineRepository.delete(SubLineEntity.builder()
                .subLineNo(subLineNo)
                .build());
        System.out.println("subLineRepository : "+ subLineRepository);
    }
}
