package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.approval.ApprovalSaveDto;
import com.ohalfmoon.firework.model.ApprovalEntity;
import com.ohalfmoon.firework.persistence.ApprovalRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
/**
 * packageName    : com.ohalfmoon.firework.service
 * fileName       : ApprovalServiceTest
 * author         : 오상현
 * date           : 2023/06/08
 * description    : 결재 Service 테스트
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/08        오상현           최초 생성
 */
@SpringBootTest
public class ApprovalServiceTest {
    @Autowired
    private ApprovalService approvalService;

    @Autowired
    ApprovalRepository approvalRepository;

    @Test
    @DisplayName("결재 등록 테스트")
    void register() {
        ApprovalSaveDto saveDto = ApprovalSaveDto.builder()
                .approvalName("기안 작성 테스트")
                .formNo(1L)
                .lineNo(1L)
                .docboxNo(1L)
                .approContent("서비스테스트작성중")
                .userNo(1L)
                .build();
        Long saveId = approvalService.register(saveDto);
        ApprovalEntity approvalEntity = approvalRepository.findByApprovalNo(saveId);

    }
}
