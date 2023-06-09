package com.ohalfmoon.firework.repository;

import com.ohalfmoon.firework.model.*;
import com.ohalfmoon.firework.persistence.ApprovalRepository;
import org.apache.juli.logging.Log;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class ApprovalRepositoryTests {
    @Autowired
    ApprovalRepository approvalRepository;

    @Test
    public void approvalSave() {
        approvalRepository.save(ApprovalEntity.builder()
                        .approvalName("다시만드는기안")
                        .formEntity(FormEntity.builder().formNo(1L).build())
                        .masterLineEntity(MasterLineEntity.builder().lineNo(1L).build())
                        .docboxEntity(DocboxEntity.builder().docboxNo(1L).build())
                        .approContent("가나다라마바사아자차카타파하")
                        .memberEntity(MemberEntity.builder().userNo(1L).build())
                .build());
    }

    @Test
    public void approvalList() {
        List<ApprovalEntity> test = approvalRepository.findAllByMemberEntity(MemberEntity.builder().userNo(1L).build());
        test.forEach(System.out::println);
    }

    @Test
    public void getApproval() {
        ApprovalEntity test = approvalRepository.findByApprovalName("다시만드는기안");
        System.out.println(test);
    }

    @Test
    public void removeTest() {
        approvalRepository.delete(ApprovalEntity.builder().approvalNo(10L).build());
    }
}
