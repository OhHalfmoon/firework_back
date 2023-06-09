package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.approval.*;
import com.ohalfmoon.firework.model.ApprovalEntity;
import com.ohalfmoon.firework.persistence.ApprovalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * packageName    : com.ohalfmoon.firework.service
 * fileName       : ApprovalService
 * author         : 오상현
 * date           : 2023/06/08
 * description    : 결재 Service
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/08        오상현           최초 생성, save관련 (임시저장, 제출)
 * 2023/06/09        오상현           update관련 (문서수정, 임시저장->제출, 결재완료)
 */
@Service
@RequiredArgsConstructor
public class ApprovalService {
    private final ApprovalRepository approvalRepository;

    //임시저장
    @Transactional
    public Long storage(ApprovalSaveDto saveDto) {
        return approvalRepository.save(saveDto.toStorageApproval()).getApprovalNo();
    }

    //기안 제출(결재)
    @Transactional
    public Long register(ApprovalSaveDto saveDto) {
        return approvalRepository.save(saveDto.toSaveApproval()).getApprovalNo();
    }

    //기안명을 통한 단일조회
    public ApprovalResponseDto getName (final String approvalName) {
        ApprovalEntity approvalEntity = approvalRepository.findByApprovalName(approvalName);
        return new ApprovalResponseDto(approvalEntity);
    }

    //기안번호를 통한 단일조회
    public ApprovalResponseDto get (final Long approvalNo) {
        ApprovalEntity approvalEntity = approvalRepository.findByApprovalNo(approvalNo);
        return new ApprovalResponseDto(approvalEntity);
    }

//    public List getMyList (final Long userNo) {
//        return approvalRepository.findAllByMemberEntity(MemberEntity.builder().userNo(userNo).build());
//    }

    //결재 서류 수정
    @Transactional
    public ApprovalResponseDto update(long approvalNo, ApprovalUpdateDto updateDto) {
        ApprovalEntity approvalEntity = approvalRepository.findByApprovalNo(approvalNo);

        approvalEntity.update(
                updateDto.getApprovalName(),
                updateDto.getLineNo(),
                updateDto.getDocboxNo(),
                updateDto.getApproContent()
//                ,
//                updateDto.isStorage(),
//                updateDto.getApprovalState()
        );

        return approvalEntity.toDto();
    }

    //임시저장중인 기안을 제출
    @Transactional
    public  ApprovalResponseDto updateStorage(long approvalNo, ApprovalStorageDto storageDto) {
        ApprovalEntity approvalEntity = approvalRepository.findByApprovalNo(approvalNo);
        approvalEntity.updateStorage(
                storageDto.isStorage(),
                storageDto.getApprovalState()
        );

        return approvalEntity.toDto();
    }

    //기안을 결재완료로 변경
    @Transactional
    public  ApprovalResponseDto updateState(long approvalNo, ApprovalStateDto stateDto) {
        ApprovalEntity approvalEntity = approvalRepository.findByApprovalNo(approvalNo);
        approvalEntity.updateState(
                stateDto.getApprovalState()
        );

        return approvalEntity.toDto();
    }

    //기안 삭제
    @Transactional
    public void delete(Long approvalNo) {
        ApprovalEntity approvalEntity = approvalRepository.findByApprovalNo(approvalNo);
        approvalRepository.delete(approvalEntity);
    }

}
