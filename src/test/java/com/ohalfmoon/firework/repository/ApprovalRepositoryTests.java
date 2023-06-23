package com.ohalfmoon.firework.repository;

import com.ohalfmoon.firework.model.*;
import com.ohalfmoon.firework.persistence.ApprovalRepository;
import com.ohalfmoon.firework.persistence.MasterLineRepository;
import com.ohalfmoon.firework.persistence.SubLineRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
/**
 * packageName    : com.ohalfmoon.firework.repository
 * fileName       : ApprovalRepositoryTests
 * author         : 오상현
 * date           : 2023/06/07
 * description    : 기안 Repository 테스트
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/07        오상현            최초 생성
 */
@SpringBootTest
@Slf4j
public class ApprovalRepositoryTests {
    @Autowired
    ApprovalRepository approvalRepository;
    @Autowired
    SubLineRepository subLineRepository;

    @Test
    public void approvalSave() {
        approvalRepository.save(ApprovalEntity.builder()
                        .approvalName("다시만드는기안")
                        .formEntity(FormEntity.builder().formNo(1L).build())
                        .masterLineEntity(MasterLineEntity.builder().lineNo(1L).build())
                        .docboxEntity(DocboxEntity.builder().docboxNo(1L).build())
                        .approContent("가나다라마바사아자차카타파하")
                        .memberEntity(MemberEntity.builder().userNo(1L).build())
                        .approvalState(0)
                .build());
    }

    @Test
    public void approvalList() {
        List<ApprovalEntity> test = approvalRepository.findAllByMemberEntity(MemberEntity.builder().userNo(1L).build());
        test.forEach(System.out::println);
    }

    @Test
    public void getApprovalName() {
        ApprovalEntity test = approvalRepository.findByApprovalName("다시만드는기안");
        System.out.println(test);
    }
    @Test
    public void getApproval() {


        ApprovalEntity test = approvalRepository.findByApprovalNo(37L);
        MasterLineEntity mle = test.getMasterLineEntity();

        System.out.println(mle.getMemberEntity().getName());
        System.out.println(mle.getMemberEntity().getPositionEntity().getPositionName());
        subLineRepository.findAllByMasterLineEntity_LineNo(mle.getLineNo()).forEach(sle -> System.out.println(sle.getMemberEntity().getName() + sle.getMemberEntity().getPositionEntity().getPositionName()));
//        System.out.println(test);

    }

    @Test
    public void removeTest() {
        approvalRepository.delete(ApprovalEntity.builder().approvalNo(10L).build());
    }

    @Test
    public void masterlineTest() {
        Long lineNo = 1L;
        List<ApprovalEntity> list = approvalRepository.findAllByMasterLineEntity(MasterLineEntity.builder().lineNo(lineNo).build());
        log.info("리스트확인{}", list);
    }

    @Test
    public void approvalStateListTest() {
        int approvalState = 0;
        List<ApprovalEntity> list = approvalRepository.findAllByMemberEntityAndApprovalState(MemberEntity.builder().userNo(15L).build(), approvalState);
        log.info("리스트확인{}", list, list.size());
    }
}

