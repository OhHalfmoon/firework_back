package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.approval.*;
import com.ohalfmoon.firework.model.ApprovalEntity;
import com.ohalfmoon.firework.persistence.ApprovalRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

/**
 * packageName    : com.ohalfmoon.firework.service
 * fileName       : ApprovalServiceTest
 * author         : 오상현
 * date           : 2023/06/08
 * description    : 결재 Service 테스트
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/08        오상현            최초 생성
 * 2023/06/09        오상현            상태변경update 테스트
 */
@SpringBootTest
@Slf4j
public class ApprovalServiceTest {
    @Autowired
    private ApprovalService approvalService;

    @Autowired
    ApprovalRepository approvalRepository;

    @Test
    @DisplayName("등록 테스트")
    void register() {
        ApprovalSaveDto saveDto = ApprovalSaveDto.builder()
                .approvalName("기안 제출 테스트")
                .formNo(1L)
                .lineNo(1L)
                .docboxNo(1L)
                .approContent("서비스테스트작성중")
                .userNo(1L)
                .approvalState(0)
                .build();
        Long saveId = approvalService.register(saveDto);
        ApprovalEntity approvalEntity = approvalRepository.findByApprovalNo(saveId);
    }

    @Test
    @DisplayName("결재내용 수정 테스트")
    void update() {
        Long updateId = approvalService.get(19L).getApprovalNo();
        ApprovalUpdateDto updateDto = ApprovalUpdateDto.builder()
                .approvalName("삭제예정")
                .lineNo(3L)
                .docboxNo(1L)
                .approContent("삭제예정 기안")
                .build();
        Long updateNo = approvalService.update(updateId, updateDto);
    }

    @Test
    @DisplayName("결재 상태변경 테스트")
    void updateState() {
        Long updateId = approvalService.get(19L).getApprovalNo();
        ApprovalStateDto stateDto = ApprovalStateDto.builder().approvalState(2).build();
        Long updateStateNo = approvalService.updateState(updateId, stateDto);
    }


    @Test
    @DisplayName("결재명 단일조회 테스트")
    void getNameTest() {
        final String approvalName = "기안 제출 테스트";
        ApprovalResponseDto responseDto = approvalService.getName(approvalName);
        System.out.println(responseDto);
    }

    @Test
    @DisplayName("결재번호 단일조회 테스트")
    void getTest() {
        final Long approvalNo = 3L;
        ApprovalResponseDto responseDto = approvalService.get(approvalNo);
        System.out.println(responseDto);
    }

    @Test
    @DisplayName("직원번호를 통해 전체 리스트 조회 테스트")
    void getListTest() {
        final Long userNo = 1L;
        List<ApprovalResponseDto> listDto = approvalService.getMyList(userNo);
        log.info("나의 리스트: {}", listDto);
        log.info("나의 리스트: {}", listDto.size());
//        listDto.forEach(System.out::println);

    }
    
    @Test
    @DisplayName("결재삭제 테스트")
    void deleteTest() {
        final Long approvalNo = 52L;
        approvalService.delete(approvalNo);
        System.out.println("삭제완료");
    }

    @Test
    @DisplayName("결재자리스트 테스트")
    void getApprovalMemberTest() {
        final Long approvalNo = 56L;
        List<ApprovalLineDto> lineDtos = approvalService.getApprovalUserName(approvalNo);
        log.info("subLineResponseDTOList: {}",lineDtos);
        log.info("subLineResponseDTOList: {}",lineDtos.size());
    }

    //    @Test
//    @DisplayName("결재 임시저장 테스트")
//    void storage() {
//        ApprovalSaveDto saveDto = ApprovalSaveDto.builder()
//                .approvalName("기안 임시저장")
//                .formNo(1L)
//                .lineNo(1L)
//                .docboxNo(1L)
//                .approContent("서비스임시저장작성중")
//                .userNo(1L)
//                .build();
//        Long saveId = approvalService.storage(saveDto);
//        ApprovalEntity approvalEntity = approvalRepository.findByApprovalNo(saveId);
//
//    }

    //    @Test
//    @DisplayName("임시저장 상태변경 테스트")
//    void updateStorage() {
//        Long updateId = approvalService.get(17L).getApprovalNo();
//        ApprovalStorageDto storageDto = ApprovalStorageDto.builder().build();
//        ApprovalResponseDto approvalResponseDto = approvalService.updateStorage(updateId, storageDto);
//    }

}
