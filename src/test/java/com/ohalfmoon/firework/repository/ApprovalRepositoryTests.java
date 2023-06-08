package com.ohalfmoon.firework.repository;

import com.ohalfmoon.firework.model.*;
import com.ohalfmoon.firework.persistence.ApprovalRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
@Slf4j
public class ApprovalRepositoryTests {
    @Autowired
    ApprovalRepository approvalRepository;

    @Test
    public void approvalSave() {
//        approvalRepository.save(ApprovalEntity.builder()
//                        .approvalName("다시만드는기안")
//                        .formEntity(FormEntity.builder().formNo(1L).build())
//                        .masterLineEntity(MasterLineEntity.builder().lineNo(1L).build())
//                        .docboxEntity(DocboxEntity.builder().docboxNo(1L).build())
//                        .approContent("가나다라마바사아자차카타파하")
//                        .memberEntity(MemberEntity.builder().userNo(1L).build())
//                        .regdate(new Date())
//                .build());
    }
}
