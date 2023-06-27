package com.ohalfmoon.firework.repository;

import com.ohalfmoon.firework.model.ApprovalEntity;
import com.ohalfmoon.firework.model.AttachEntity;
import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.persistence.AttachRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class AttachRepositoryTests {
    @Autowired
    AttachRepository attachRepository;

    @Test
    public void attachList() {
        List<AttachEntity> test = attachRepository.findAllByApprovalEntity(ApprovalEntity.builder().approvalNo(104L).build());
        log.info("크기확인{}",test.size());
    }
}
