package com.ohalfmoon.firework.repository;

import com.ohalfmoon.firework.model.MasterLineEntity;
import com.ohalfmoon.firework.persistence.MasterLineRepository;
import com.ohalfmoon.firework.persistence.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * packageName    : com.ohalfmoon.firework.repository
 * fileName       : MasterLineRepositoryTests
 * author         : 이지윤
 * date           : 2023/06/09
 * description    : 마스터 라인 레파지토리 테스트
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/09        이지윤           최초 생성
 */

@SpringBootTest
@Slf4j
public class MasterLineRepositoryTests {
    @Autowired
    private MasterLineRepository masterLineRepository;

    @Autowired
    private MemberRepository memberRepository;

    @After("")
    public void cleanup() {
        masterLineRepository.deleteAll();
    }

    // 마스터 라인 생성 - 성공
    @Test
    public void testSave(){
        String lineName = "팀장님 부재시 - 수정";
        Long userNo = 1L;

        masterLineRepository.save(MasterLineEntity.builder()
                .lineName(lineName)
                .memberEntity(memberRepository.findById(userNo).orElseThrow(()-> new IllegalArgumentException("실패")))
                .build());
    }

    // 마스터 라인 lineNo를 통한 단일 조회 - 성공
    @Test
    public void testFindByLineNo(){
        Long lineNo = 1L;

        MasterLineEntity masterLineEntity = masterLineRepository.findById(lineNo).orElse(null);
        log.info("{}" ,masterLineEntity);
    }

    // 마스터 라인 삭제 - 성공
    @Test
    public void testDelete(){
        Long lineNo = 2L;
        masterLineRepository.delete(MasterLineEntity.builder()
                .lineNo(lineNo)
                .build());
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void testUpdate() {
        MasterLineEntity me = masterLineRepository.findById(6L).orElseThrow(()-> new RuntimeException("없음"));
        me.update("팀장님 계실때");
    }

    // 마스터 라인 리스트 조회 - 성공
    @Test
    public void testList() {
        Long userNo = 1L;
        List<MasterLineEntity> lists = masterLineRepository.findByMemberEntity_UserNo(userNo);
        log.info("{}" , lists);
    }
}