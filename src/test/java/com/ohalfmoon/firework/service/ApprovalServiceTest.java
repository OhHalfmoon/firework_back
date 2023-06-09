package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.approval.ApprovalResponseDto;
import com.ohalfmoon.firework.dto.approval.ApprovalSaveDto;
import com.ohalfmoon.firework.dto.approval.ApprovalUpdateDto;
import com.ohalfmoon.firework.model.ApprovalEntity;
import com.ohalfmoon.firework.persistence.ApprovalRepository;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ApprovalServiceTest {
    @Autowired
    private ApprovalService approvalService;

    @Autowired
    ApprovalRepository approvalRepository;

    @Test
    @DisplayName("결재 임시저장 테스트")
    void storage() {
        ApprovalSaveDto saveDto = ApprovalSaveDto.builder()
                .approvalName("기안 임시저장 테스트")
                .formNo(1L)
                .lineNo(1L)
                .docboxNo(1L)
                .approContent("서비스임시저장작성중")
                .userNo(1L)
                .build();
        Long saveId = approvalService.storage(saveDto);
        ApprovalEntity approvalEntity = approvalRepository.findByApprovalNo(saveId);

    }

    @Test
    @DisplayName("결재 등록 테스트")
    void register() {
        ApprovalSaveDto saveDto = ApprovalSaveDto.builder()
                .approvalName("기안 제출 테스트")
                .formNo(1L)
                .lineNo(1L)
                .docboxNo(1L)
                .approContent("서비스테스트작성중")
                .userNo(1L)
                .build();
        Long saveId = approvalService.register(saveDto);
        ApprovalEntity approvalEntity = approvalRepository.findByApprovalNo(saveId);
    }

    @Test
    @DisplayName("결재내용 수정 테스트")
    void updateStorage() {
        Long updateId = approvalService.get("기안수정테스트").getApprovalNo();
        ApprovalUpdateDto updateDto = ApprovalUpdateDto.builder()
                .approvalName("기안수정/임시저장테스트")
                .lineNo(3L)
                .docboxNo(1L)
                .approContent("수정이완료되었습니다")
                .build();
        ApprovalResponseDto approvalResponseDto = approvalService.updateStorage(updateId, updateDto);
    }

    @Test
    @DisplayName("결재 단일조회 테스트")
    void getTest() {
        final String approvalName = "기안 제출 테스트";
        ApprovalResponseDto responseDto = approvalService.get(approvalName);
        System.out.println(responseDto);
    }
}
